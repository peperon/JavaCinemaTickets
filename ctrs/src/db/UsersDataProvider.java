package db;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class UsersDataProvider extends BaseDataProvider {
	
	public UsersDataProvider() {
		super();
	}
	
	public List<User> getUsers() throws SQLException {
		return getEntityManager().createQuery("SELECT u FROM User u", User.class).getResultList();
	}
	
	public void saveUser(User user) {
		saveObject(user);
	}
}
