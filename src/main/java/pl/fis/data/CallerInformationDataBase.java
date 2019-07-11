package pl.fis.data;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CallerInformationDataBase
{
	private List<User> users;

	public CallerInformationDataBase()
	{
		users = new ArrayList<>();
	}

	public void addUser(User user)
	{
		users.add(user);
	}
	
	public List<User> getUsers()
	{
		return users;
	}
}
