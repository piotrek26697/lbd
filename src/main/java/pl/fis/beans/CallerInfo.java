package pl.fis.beans;

import java.util.Locale;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import pl.fis.data.UserRoles;
import pl.fis.qualifiers.LanguageFormatter;

@RequestScoped
public class CallerInfo
{
	private long id;
	private String name;
	private String lastName;
	private UserRoles userRole;
	private Locale userLocale;
	private String sessionID;
	private String ipAddress;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public UserRoles getUserRole()
	{
		return userRole;
	}

	public void setUserRole(UserRoles userRole)
	{
		this.userRole = userRole;
	}

//	@Produces
//	@LanguageFormatter	TODO
	public Locale getUserLocale()
	{
		return userLocale;
	}

	public void setUserLocale(Locale userLocale)
	{
		this.userLocale = userLocale;
	}

	public String getSessionID()
	{
		return sessionID;
	}

	public void setSessionID(String sessionID)
	{
		this.sessionID = sessionID;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

}