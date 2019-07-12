package pl.fis.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.fis.beans.CallerInfo;
import pl.fis.data.User;
import pl.fis.data.UserRoles;

@WebFilter(filterName = "permissionFilter")
public class PermissionFilter implements Filter
{
	
	@Inject
	private CallerInfo callerInfo;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		//User user = (User) req.getSession().getAttribute("loggedUser");

		boolean permission = false;
		String forbiddenURI, requestURI;
		
		String test = callerInfo.getName();
		switch (callerInfo.getUserRole())
		{
		case ADMIN:
			permission = true;
			break;
		case STATISTIC_MANAGER:
			forbiddenURI = req.getContextPath() + "/user-monitor";
			requestURI = req.getRequestURI();
			if (!forbiddenURI.equals(requestURI))
				permission = true;
			break;
		case STATISTIC_VIEWER:
			requestURI = req.getRequestURI();
			if (!requestURI.equals(req.getContextPath()+"/download") && !requestURI.equals(req.getContextPath()+"/user-monitor"))
				permission = true;
			break;
		}

		if (permission)
			chain.doFilter(request, response);
		else
			unauthorized(resp, "Access denied");
	}

	private void unauthorized(HttpServletResponse response, String message) throws IOException
	{
		response.sendError(403, message);
	}

}
