package db;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.User;

public class UsersDataProvider extends BaseDataProvider {
	
	public UsersDataProvider() {
		super();
	}
	
	public List<User> getUsers() throws SQLException {
		EntityManager em = getEntityManager();
		List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
		closeEntityManager(em);
		return users;
	}
	
	public List<User> getRegularUsers() throws SQLException{
		EntityManager entityManager = getEntityManager();
		try {
			List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.userTypeId = 1", User.class)
					.getResultList();
			return users;			
		}
		catch(NoResultException ex){
			return null;
		}
		finally{
			closeEntityManager(entityManager);
		}
	}
	
	public void saveUser(User user) {
		saveObject(user);
	}
	
	public User getUserByName(String userName) {
		EntityManager em = getEntityManager();
		try{
			User user = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class).
				setParameter("userName", userName).getSingleResult();
			return user;
		}
		catch(NoResultException ex){
			return null;
		}
		finally{
			closeEntityManager(em);
		}				
	}
}
