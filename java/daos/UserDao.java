package daos;

import model.User;

public interface UserDao {
	User login(String email, String password);
	boolean register(String email, String password);
	boolean existsEmail(String email);
}