package db;

import java.util.List;

import javax.persistence.EntityManager;

import model.Hall;
import model.HallLayout;

public class HallDataProvider extends BaseDataProvider {
	
	public HallDataProvider() {
		super();
	}
	
	public List<Hall> getHalls() {
		EntityManager em = getEntityManager();
		List<Hall> halls = em.createQuery("SELECT h FROM Hall h", Hall.class).getResultList();
		closeEntityManager(em);
		return halls;
	}
	
	public Hall saveHall(Hall hall) {
		return (Hall) saveObject(hall);
	}
	
	public Hall getHallById(int id) {
		return getEntityManager().createQuery("SELECT h FROM Hall h WHERE h.id = :id", Hall.class).
				setParameter("id", id).getSingleResult();
	}
	
	public List<HallLayout> getHallLayout() {
		EntityManager em = getEntityManager();
		List<HallLayout> layout = em.createQuery("SELECT l FROM HallLayout l", HallLayout.class).getResultList();
		closeEntityManager(em);
		return layout;
	}
}
