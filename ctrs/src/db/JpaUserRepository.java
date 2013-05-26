package db;

import java.util.List;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUserRepository implements UserRepository {
	private EntityManager entityManager;
	
	public JpaUserRepository(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ctrs");
		this.entityManager = emf.createEntityManager();
	}
	
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub

	}

}
