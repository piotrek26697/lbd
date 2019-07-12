package pl.fis.data;

public class User
{
	private int id;
	private String name;
	private String lastName;
	private String login;
	private String password;
	private UserRoles userRoles;
	
	public String getLogin()
	{
		return login;
	}
	public void setLogin(String login)
	{
		this.login = login;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
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

}
