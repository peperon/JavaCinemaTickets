package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements Serializable {
	
	@Id
	@SequenceGenerator(name="SEQ", sequenceName="user_id_seq", allocationSize=1)  
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="SEQ")  
	@Basic
	@Column(name="id")
	private Integer id;
	@Basic
	@Column(name="user_name")
	private String userName;
	@Basic
	@Column(name="first_name")
	private String firstName;
	@Basic
	@Column(name="last_name")
	private String lastName;
	@Basic
	@Column(name="password")
	private String password;
	@Temporal(TemporalType.DATE)
	@Column(name="register_date")
	private Date registerDate;
	@Basic
	@Column(name="user_type_id")
	private int userTypeId;
	
	public User() { }

	public User(Integer id, String userName, String firstName, String lastName,
			String password, Date registerDate, int userTypeId) {
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.registerDate = registerDate;
		this.userTypeId = userTypeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	};
	
}