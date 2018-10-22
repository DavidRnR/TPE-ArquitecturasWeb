package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = Article.FIND_ALL, query="SELECT a FROM Article a")
@NamedQuery(name = Article.FIND_BY_ID, query="SELECT a FROM Article a WHERE a.id = ?1")

@Entity
@Table(name="Article")
public class Article {

	public static final String FIND_ALL = "Article.findAll";
	public static final String FIND_BY_ID = "Article.findById";
	
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
		this.keyWords = new ArrayList<KeyWord>();
	}
	
	public boolean addKeyWord (KeyWord keyWord) {
		return this.keyWords.add(keyWord);
	}
	
	public boolean removeKeyWord (KeyWord keyWord) {
		return this.keyWords.remove(keyWord);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<KeyWord> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<KeyWord> keyWords) {
		this.keyWords = keyWords;
	}
}
