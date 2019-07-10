package pl.fis.Listeners;

import java.util.logging.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener
{
	final private Logger log = Logger.getLogger(getClass().getName());

	@Override
	public void requestInitialized(ServletRequestEvent sre)
	{
		log.info("Request preffered language: "+sre.getServletRequest().getLocale().getDisplayLanguage());
		ServletRequestListener.super.requestInitialized(sre);
	}
	
	
}
