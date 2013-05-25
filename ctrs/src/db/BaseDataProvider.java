package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDataProvider {
	
	protected EntityManager entityManager;
	
	public BaseDataProvider() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ctrs");
		this.entityManager = emf.createEntityManager();
	}
	
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	protected void saveObject(Object object) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(object);
		getEntityManager().flush();
		getEntityManager().getTransaction().commit();
	}
	
	protected void closeEntityManager() {
		this.entityManager.close();
	}
}
