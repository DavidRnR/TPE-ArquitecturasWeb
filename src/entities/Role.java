package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQuery(name = Role.FIND_ALL, query="SELECT r FROM Role r")
@NamedQuery(name = Role.FIND_BY_ID, query="SELECT r FROM Role r WHERE r.id = ?1")
@NamedQuery(name = Role.DELETE_TABLE, query="DELETE FROM Role r") 

@Entity
@Table(name="Role")
public class Role {

	public static final String FIND_ALL = "Role.findAll";
	public static final String FIND_BY_ID = "Role.findById";
	public static final String DELETE_TABLE = "Role.deleteTable";
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Role() {}

	public Role(String name) {
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public boolean isEvaluador () {
		return this.name.equals("evaluador");
	}
	
	@JsonIgnore
	public boolean isAutor () {
		return this.name.equals("autor");
	}
	
	@Override
	public String toString () {
		return this.name;
	}
}
