package db;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import model.Hall;
import model.Movie;
import model.User;

public class MovieDataProvider extends BaseDataProvider {
	
	public MovieDataProvider() {
		super();
	}
	
	public List<Movie> getMovies(boolean onlyActive) {
		EntityManager em = getEntityManager();
		List<Movie> movies = em.createQuery("SELECT m FROM Movie m ORDER BY m.projection ASC", 
				Movie.class).getResultList();
		if (!onlyActive) {
			closeEntityManager(em);
			return movies;
		} else {
			List<Movie> activeMovies = new ArrayList<Movie>();
			Timestamp currentTime = new Timestamp(new Date().getTime());
			for (Movie movie : movies) {
				if (movie.getProjection().getTime() >= currentTime.getTime()) {
					activeMovies.add(movie);
				}
			}
			closeEntityManager(em);
			return activeMovies;
		}
	}
	
	public Movie saveMovie(Movie movie) {
		Object object = saveObject(movie);
		return (Movie) object;
	}
	
	public Movie getMovieById(int id) {
		EntityManager em = getEntityManager();
		Movie movie = em.createQuery("SELECT m FROM Movie m WHERE m.id = :id", Movie.class).
				setParameter("id", id).getSingleResult();
		closeEntityManager(em);
		return movie;
	}
	
	public void updateMovie(Movie movie) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		Movie m = em.find(Movie.class, movie.getId());
		m.setDescription(movie.getDescription());
		m.setHallId(movie.getHallId());
		m.setLength(movie.getLength());
		m.setProjection(movie.getProjection());
		m.setTitle(movie.getTitle());
		m.setYear(movie.getYear());
		em.getTransaction().commit();
		closeEntityManager(em);
	}
}
