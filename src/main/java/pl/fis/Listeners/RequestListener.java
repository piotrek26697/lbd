package pl.fis.listeners;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import pl.fis.data.CallerInformationDataBase;
import pl.fis.data.User;

@WebListener
public class RequestListener implements ServletRequestListener
{
	// final private Logger log = Logger.getLogger(getClass().getName());

	@Inject
	private CallerInformationDataBase db;

	@Override
	public void requestInitialized(ServletRequestEvent sre)
	{
		// log.info("Request preffered language:
		// "+sre.getServletRequest().getLocale().getDisplayLanguage());

		User user = new User();
		user.setId(db.getUsers().size() + 1);
		user.setName("?");

		ServletRequestListener.super.requestInitialized(sre);
	}

}
