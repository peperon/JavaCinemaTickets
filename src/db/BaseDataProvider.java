package db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseDataProvider {
	
	protected EntityManagerFactory emf;
	
	public BaseDataProvider() {
		this.emf = Persistence.createEntityManagerFactory("ctrs");
	}
	
	/*protected EntityManagerFactory getEntityManagerFactory() {
		return this.emf;
	}*/
	
	protected EntityManager getEntityManager() {
		if (this.emf == null) {
			this.emf = Persistence.createEntityManagerFactory("ctrs");
		}
		return this.emf.createEntityManager();
	}
	
	protected void closeEntityManager(EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}
	
	protected Object saveObject(Object object) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(object);
		em.flush();
		em.getTransaction().commit();
		em.detach(object);
		closeEntityManager(em);
		return object;
	}

}
