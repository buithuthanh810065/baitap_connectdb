package daos;

import model.User;

public interface UserDao {
//	User findByUserName(String username);
	User login(String username, String password);
	boolean register(String username, String password);
}
