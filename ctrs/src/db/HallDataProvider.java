package db;

import java.util.List;

import model.Hall;

public class HallDataProvider extends BaseDataProvider {
	
	public HallDataProvider() {
		super();
	}
	
	public List<Hall> getHalls() {
		return getEntityManager().createQuery("SELECT h FROM Hall h", Hall.class).getResultList();
	}
	
	public Hall saveHall(Hall hall) {
		return (Hall) saveObject(hall);
	}
	
	public Hall getHallById(int id) {
		return getEntityManager().createQuery("SELECT h FROM Hall h WHERE h.id = :id", Hall.class).
				setParameter("id", id).getSingleResult();
	}
}
