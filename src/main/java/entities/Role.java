package entities;

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
	}
}
