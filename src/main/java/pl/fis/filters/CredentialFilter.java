package pl.fis.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

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

@WebFilter(filterName = "credentialFilter",
initParams = {
		@WebInitParam(name = "username", value = "admin"),
		@WebInitParam(name = "password", value = "admin")
		})
public class CredentialFilter implements Filter
{
	final private Logger logger = Logger.getLogger(getClass().getName());

	private String username = "";

	private String password = "";

	private String realm = "Protected";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		username = filterConfig.getInitParameter("username");
		password = filterConfig.getInitParameter("password");
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

				if (basic.equalsIgnoreCase("Basic"))
				{
					try
					{
						String credentials = new String(Base64.getDecoder().decode(st.nextToken()));
						logger.log(Level.INFO, "Credentials: " + credentials);
						int p = credentials.indexOf(":");
						if (p != -1)
						{
							String _username = credentials.substring(0, p).trim();
							String _password = credentials.substring(p + 1).trim();

							if (!username.equals(_username) || !password.equals(_password))
							{
								unauthorized(resp, "Bad credentials");
							}

							chain.doFilter(request, response);
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
