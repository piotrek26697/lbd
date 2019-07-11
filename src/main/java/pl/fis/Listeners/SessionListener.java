package pl.fis.listeners;

import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener
{
	final private Logger log = Logger.getLogger(getClass().getName());

	@Override
	public void sessionCreated(HttpSessionEvent se)
	{
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		log.info("Session created on " + timestamp + "\tSession ID: " + se.getSession().getId());

		HttpSessionListener.super.sessionCreated(se);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se)
	{
		String sessionID = se.getSession().getId();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		log.info("Session destroyd on " + timestamp + "\tSession ID: " + sessionID);
		HttpSessionListener.super.sessionDestroyed(se);
	}

}
