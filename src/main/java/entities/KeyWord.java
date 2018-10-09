package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="KeyWord")
public class KeyWord {


	@Id
	@GeneratedValue
	private int id;
	private String word;
	
	@ManyToMany
	private List<User> users;
	@ManyToMany
	private List<Article> articles;
	
	public KeyWord() {}

	public KeyWord(String word) {
		this.word = word;
		this.users = new ArrayList<User>();
		this.articles = new ArrayList<Article>();
	}
	
	public boolean addUser (User user) {
		return this.users.add(user);
	}
	
	public boolean removeUser (User user) {
		return this.users.remove(user);
	}

	public boolean addArticle (Article article) {
		return this.articles.add(article);
	}

	public boolean removeArticle (Article article) {
		return this.articles.remove(article);
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
