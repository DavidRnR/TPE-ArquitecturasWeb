package entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQuery(name = Category.FIND_ALL, query="SELECT c FROM Category c")
@NamedQuery(name = Category.FIND_BY_ID, query="SELECT c FROM Category c WHERE c.id = ?1")
@NamedQuery(name = Category.FIND_BY_NAME, query="SELECT c FROM Category c WHERE c.name = ?1")
@NamedQuery(name = Category.DELETE_TABLE, query="DELETE FROM Category c") 

@Entity
@Table(name="category")
public abstract class Category {

	public static final String FIND_ALL = "Category.findAll";
	public static final String FIND_BY_ID = "Category.findById";
	public static final String FIND_BY_NAME = "Category.findByName";
	public static final String DELETE_TABLE = "Category.deleteTable";

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@JsonIgnore
	@OneToMany
	@JoinColumn(name = "category_id")
	private List<Work> articles;

	public Category() {}

	public Category(String name) {
		this.name = name;
		this.articles = new ArrayList<Work>();
	}

	public boolean addArticle (Work article) {
		return this.articles.add(article);
	}

	public boolean removeArticle (Work article) {
		return this.articles.remove(article);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Work> getArticles() {
		return articles;
	}

	public void setArticles(List<Work> articles) {
		this.articles = articles;
	}

	@Override
	public String toString () {
		return this.name;
	}

	public abstract boolean containsKeyWordsNeeded (List<KeyWord> artKw, List<KeyWord> userKw);
}
