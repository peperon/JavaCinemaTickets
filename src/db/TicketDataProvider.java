package db;

import java.util.List;

import javax.persistence.EntityManager;

import model.Ticket;

public class TicketDataProvider extends BaseDataProvider {
	
	public TicketDataProvider() {
		super();
	}
	
	public List<Ticket> getTickets() {
		EntityManager em = getEntityManager();
		List<Ticket> tickets = em.createQuery("SELECT t FROM Ticket t ORDER BY t.date", Ticket.class).getResultList();
		closeEntityManager(em);
		return tickets;
	}
	
	public List<Ticket> getTicketsByUser(int userId){
		EntityManager entityManager = getEntityManager();
		List<Ticket> tickets = entityManager.createQuery("SELECT t FROM Ticket t WHERE t.user.id = :userId", Ticket.class)
				.setParameter("userId", userId)
				.getResultList();
		
		return tickets;
	}
	
	public Ticket saveTicket(Ticket ticket) {
		Object object = saveObject(ticket);
		return (Ticket) object;
	}
	
	public void updateTicket(Ticket ticket) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Ticket t = em.find(Ticket.class, ticket.getId());
		t.setUsed(ticket.isUsed()); // only this field will be updated
		em.getTransaction().commit();
		closeEntityManager(em);
	}
	
	public Ticket getTicketById(int id) {
		EntityManager em = getEntityManager();
		Ticket ticket = em.find(Ticket.class, id);
		closeEntityManager(em);
		return ticket;
	}
}
