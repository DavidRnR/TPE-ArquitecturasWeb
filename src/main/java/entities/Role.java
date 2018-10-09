package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Role")
public class Role {

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
