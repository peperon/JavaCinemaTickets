package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import model.User;

public class DatabaseService {
	
	public static Context envCtx;
	public static DataSource ds;
	
	public DatabaseService() throws ServletException  {
		try {
			envCtx = (Context) new InitialContext().lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/ctrs");
		} catch(NamingException e) { 
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}
	
	private Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	private void close(ResultSet rs, PreparedStatement ps, Connection connection) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch(SQLException se) { 
			se.printStackTrace(); 
		}
	}
	public List<User> getUsers() throws SQLException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ctrs");
		EntityManager em = emf.createEntityManager();
		List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
		em.close();
		return users;
	}
	
}
