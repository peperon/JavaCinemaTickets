package db;

import java.sql.SQLException;
import java.util.List;
import model.User;
import javax.persistence.EntityManager;

public class JpaUserRepository extends BaseDataProvider implements UserRepository {
	
	public JpaUserRepository() {
		super();
	}
	
	@Override
	public List<User> getUsers() throws SQLException {
		EntityManager em = getEntityManager();
		List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
		closeEntityManager(em);
		return users;
	}
	
	@Override
	public void saveUser(User user) {
		saveObject(user);
	}
	
	@Override
	public User getUserByName(String userName) {
		EntityManager em = getEntityManager();
		User user = em.createQuery("SELECT u FROM User u WHERE u.userName = :userName", User.class).
				setParameter("userName", userName).getSingleResult();
		closeEntityManager(em);
		return user;
	}

}
