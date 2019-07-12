package pl.fis.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Startup
public class UserInformationDataBase
{
	private List<User> users;
	final private Logger log = Logger.getLogger(getClass().getName());

	@PostConstruct
	private void donwloadUsers()
	{
		users = new ArrayList<>();
		User user1 = new User();
		user1.setId(users.size() + 1);
		user1.setName("name1");
		user1.setLogin("name1");
		user1.setPassword("name1");
		user1.setLastName("lastName1");
		user1.setUserRoles(UserRoles.STATISTIC_VIEWER);
		users.add(user1);
		User user2 = new User();
		user2.setId(users.size() + 1);
		user2.setName("name2");
		user2.setLogin("name2");
		user2.setPassword("name2");
		user2.setLastName("lastName2");
		user2.setUserRoles(UserRoles.STATISTIC_MANAGER);
		users.add(user2);
		User user3 = new User();
		user3.setId(users.size() + 1);
		user3.setName("admin");
		user3.setLogin("admin");
		user3.setPassword("admin");
		user3.setLastName("admin");
		user3.setUserRoles(UserRoles.ADMIN);
		users.add(user3);

	}

	public void addUser(User user)
	{
		users.add(user);
	}

	public List<User> getUsers()
	{
		return users;
	}

	public User getUser(String login, String password)
	{
		Optional<User> user = users.stream()
				.filter(user1 -> login.equals(user1.getLogin()) && password.equals(user1.getPassword())).findFirst();

		return user.orElse(null);
	}
}
