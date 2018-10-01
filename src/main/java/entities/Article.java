package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Article")
public class Article {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Date created;

	@ManyToOne
	private Category category;
	
	@ManyToMany
	private List<KeyWord> keyWords;
	
	public Article() {}

	public Article(String name, Category category, Date created) {
		this.name = name;
		this.category = category;
		this.created = created;
	}
}
