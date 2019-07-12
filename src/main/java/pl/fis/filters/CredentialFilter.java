package pl.fis.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.fis.data.UserInformationDataBase;
import pl.fis.beans.CallerInfo;
import pl.fis.data.User;

@WebFilter(filterName = "credentialFilter", initParams = { @WebInitParam(name = "username", value = "admin"),
		@WebInitParam(name = "password", value = "admin") })
public class CredentialFilter implements Filter
{
	final private Logger logger = Logger.getLogger(getClass().getName());

	private String realm = "Protected";

	@Inject
	private UserInformationDataBase db;
	
	@Inject
	private CallerInfo callerInfo;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		String paramRealm = filterConfig.getInitParameter("realm");
		if (paramRealm != null && paramRealm.length() > 0)
		{
			realm = paramRealm;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String authHeader = req.getHeader("Authorization");
		if (authHeader != null)
		{
			StringTokenizer st = new StringTokenizer(authHeader);
			if (st.hasMoreTokens())
			{
				String basic = st.nextToken();

				if (HttpServletRequest.BASIC_AUTH.equalsIgnoreCase(basic))
				{
					try
					{
						String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
						logger.log(Level.INFO, "Credentials: " + credentials);
						int p = credentials.indexOf(":");
						if (p != -1)
						{
							boolean loginSuccess = false;
							String _username = credentials.substring(0, p).trim();
							String _password = credentials.substring(p + 1).trim();
							List<User> list = db.getUsers();
//		!!!!!!!!			Optional<User> user = list.stream()
//								.filter(user -> _username.equals(user.getLogin()) && _password.equals(user.getPassword()))
//								.findFirst();
							for (User user : list)
							{
								if (_username.equals(user.getLogin()) && _password.equals(user.getPassword()))
								{
									//req.getSession().setAttribute("loggedUser", user);
									callerInfo.setId(System.currentTimeMillis());
									callerInfo.setName(user.getName());
									callerInfo.setLastName(user.getLastName());
									callerInfo.setSessionID(req.getSession().getId());
									callerInfo.setUserLocale(req.getLocale());
									callerInfo.setUserRole(user.getUserRoles());
									callerInfo.setIpAddress(req.getRemoteAddr());
									loginSuccess = true;
									chain.doFilter(request, response);
									break;
								}
							}
							if (!loginSuccess)
								unauthorized(resp, "Bad credentials");

						} else
						{
							unauthorized(resp, "Invalid authentication token");
						}
					} catch (UnsupportedEncodingException e)
					{
						throw new Error("Couldn't retrieve authentication", e);
					}
				}
			}
		} else
		{
			unauthorized(resp);
		}
	}

	private void unauthorized(HttpServletResponse response, String message) throws IOException
	{
		response.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
		response.sendError(401, message);
	}

	private void unauthorized(HttpServletResponse response) throws IOException
	{
		unauthorized(response, "Unauthorized");
	}

}
