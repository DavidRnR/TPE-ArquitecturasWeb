package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = Role.FIND_ALL, query="SELECT r FROM Role r")
@NamedQuery(name = Role.FIND_BY_ID, query="SELECT r FROM Role r WHERE r.id = ?1")

@Entity
@Table(name="Role")
public class Role {

	public static final String FIND_ALL = "Role.findAll";
	public static final String FIND_BY_ID = "Role.findById";
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@ManyToMany
	private List<User> users;
	
	public Role() {}

	public Role(String name) {
		this.name = name;
		this.users = new ArrayList<User>();
	}
	
	public boolean addUser (User user) {
		return this.users.add(user);
	}
	
	public boolean removeUser (User user) {
		return this.users.remove(user);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
