package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = Area.FIND_ALL, query="SELECT a FROM Area a")
@NamedQuery(name = Area.FIND_BY_ID, query="SELECT a FROM Area a WHERE a.id = ?1")

@Entity
@Table(name="Area")
public class Area {

	public static final String FIND_ALL = "Area.findAll";
	public static final String FIND_BY_ID = "Area.findById";
	
	@Id
	@GeneratedValue
	private int id;
	private String name;

	@OneToMany
	private List<User> users;
	
	public Area() {}
	
	public Area(String name) {
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
