package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Area")
public class Area {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	public Area() {}

	public Area(String name) {
		this.name = name;
	}
}
