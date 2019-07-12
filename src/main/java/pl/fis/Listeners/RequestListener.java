package pl.fis.listeners;

import javax.inject.Inject;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import pl.fis.data.UserInformationDataBase;

@WebListener
public class RequestListener implements ServletRequestListener
{
	// final private Logger log = Logger.getLogger(getClass().getName());
	
	@Inject
	private UserInformationDataBase db;

	@Override
	public void requestInitialized(ServletRequestEvent sre)
	{
		// log.info("Request preffered language:
		// "+sre.getServletRequest().getLocale().getDisplayLanguage());

		ServletRequestListener.super.requestInitialized(sre);
	}
}
