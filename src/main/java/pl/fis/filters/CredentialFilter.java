package pl.fis.filters;

import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(filterName = "credentialFilter")
public class CredentialFilter implements Filter
{
	final private Logger log = Logger.getLogger(getClass().getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
//		HttpServletRequest req = (HttpServletRequest) request;
//		String authHeader = req.getHeader("Authorization");
//		if (authHeader != null)
//		{
//			StringTokenizer tokens = new StringTokenizer(authHeader);
//			if (tokens.hasMoreTokens())
//			{
//				String basic = tokens.nextToken();
//				if (basic.equalsIgnoreCase("Basic"))
//				{
//					String credentials = new String(Base64.getDecoder().decode((tokens.nextToken())), "UTF-8");
//				}
//			}
//		}
//TODO Logging
		chain.doFilter(request, response);
	}

}
