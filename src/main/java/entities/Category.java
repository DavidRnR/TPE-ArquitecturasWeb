package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Category")
public class Category {

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
}
