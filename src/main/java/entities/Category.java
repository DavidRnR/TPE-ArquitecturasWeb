package entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQuery(name = Category.FIND_ALL, query="SELECT c FROM Category c")
@NamedQuery(name = Category.FIND_BY_ID, query="SELECT c FROM Category c WHERE c.id = ?1")
@NamedQuery(name = Category.FIND_BY_NAME, query="SELECT c FROM Category c WHERE c.name = ?1")
@NamedQuery(name = Category.DELETE_TABLE, query="DELETE FROM Category c") 

@Entity
@Table(name="Category")
public abstract class Category {
	
	public static final String FIND_ALL = "Category.findAll";
	public static final String FIND_BY_ID = "Category.findById";
	public static final String FIND_BY_NAME = "Category.findByName";
	public static final String DELETE_TABLE = "Category.deleteTable";
	
	@Id
	@GeneratedValue
	private int id;
	private String name;

	@OneToMany
	private List<Article> articles;

	public Category() {}

	public Category(String name) {
		this.name = name;
		this.articles = new ArrayList<Article>();
	}

	public boolean addArticle (Article article) {
		return this.articles.add(article);
	}

	public boolean removeArticle (Article article) {
		return this.articles.remove(article);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public abstract boolean containsKeyWordsNeeded (List<KeyWord> artKw, List<KeyWord> userKw);
}
