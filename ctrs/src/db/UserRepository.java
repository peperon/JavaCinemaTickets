package db;

import java.sql.SQLException;
import java.util.List;

import model.User;

public interface UserRepository {
	List<User> getUsers() throws SQLException;
	
	void saveUser(User user);
	
	User getUserByName(String userName);
}
