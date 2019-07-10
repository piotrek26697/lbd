package pl.fis.filters;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "informationFilter")
public class InformationFilter implements Filter
{
	final private Logger log = Logger.getLogger(getClass().getName());

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String reqFormat = req.getHeader("Accept");

		int start = Calendar.getInstance().get(Calendar.MILLISECOND);
		chain.doFilter(request, response);
		int stop = Calendar.getInstance().get(Calendar.MILLISECOND);

		log.info("Elapsed time of request: " + (stop - start) + "ms\t" + "SessionID: " + req.getSession().getId()
				+ "\tRequestID: " + req.getHeader("X-Request-ID") + "\tPreffered user language: "
				+ req.getLocale().getDisplayLanguage() + "\tRequest format: " + reqFormat + "\tResponse format: "
				+ resp.getHeader("Content-Type"));
	}
}
