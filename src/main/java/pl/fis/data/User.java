package pl.fis.data;

public class User
{
	private int id;
	private String name;
	private String lastName;
	private UserRoles userRoles;
	private String userLanguage;
	private String sessionID;
	private String ipAddress; // what type?
	public int getId()
	{
		return id;
	}
	public void setId(int id)
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
	public UserRoles getUserRoles()
	{
		return userRoles;
	}
	public void setUserRoles(UserRoles userRoles)
	{
		this.userRoles = userRoles;
	}
	public String getUserLanguage()
	{
		return userLanguage;
	}
	public void setUserLanguage(String userLanguage)
	{
		this.userLanguage = userLanguage;
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
