package db;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import utils.UserTypes;

import model.Ticket;
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
			List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.userTypeId = " + 
					UserTypes.USER_TYPE_USER, User.class).getResultList();
			return users;
		} catch(NoResultException ex) {
			return null;
		} finally {
			closeEntityManager(entityManager);
		}
	}
	
	public void saveUser(User user) {
		saveObject(user);
	}
	
	public User getUserByName(String userName) {
		EntityManager em = getEntityManager();
		try {
			User user = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class).
				setParameter("userName", userName).getSingleResult();
			return user;
		} catch(NoResultException ex){
			return null;
		} finally {
			closeEntityManager(em);
		}				
	}
	
	public User getUserById(int id) {
		EntityManager em = getEntityManager();
		User user = em.find(User.class, id);
		closeEntityManager(em);
		return user;
	}
}
