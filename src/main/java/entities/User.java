package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User {

	@Id
	@GeneratedValue
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	
	@ManyToOne
	private Area area;
	
	@ManyToMany
	private List<Role> roles;
	@ManyToMany
	private List<KeyWord> keyWords;
	@ManyToMany
	private List<Article> articles;
	
	public User() {}

	public User(String first_name, String last_name, String email, Area area) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.area = area;
	}
}
