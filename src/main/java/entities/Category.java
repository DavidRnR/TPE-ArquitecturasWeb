package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category {
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	public Category() {}

	public Category(String name) {
		this.name = name;
	}
}
