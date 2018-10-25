package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = KeyWord.FIND_ALL, query="SELECT kw FROM KeyWord kw")
@NamedQuery(name = KeyWord.FIND_BY_ID, query="SELECT kw FROM KeyWord kw WHERE kw.id = ?1")
@NamedQuery(name = KeyWord.DELETE_TABLE, query="DELETE FROM KeyWord k") 

@Entity
@Table(name="KeyWord")
public class KeyWord {

	public static final String FIND_ALL = "KeyWord.findAll";
	public static final String FIND_BY_ID = "KeyWord.findById";
	public static final String DELETE_TABLE = "KeyWord.deleteTable";

	@Id
	@GeneratedValue
	private int id;
	private String word;
	
	@ManyToMany
	private List<User> users;
	@ManyToMany
	private List<Work> articles;
	
	public KeyWord() {}

	public KeyWord(String word) {
		this.word = word;
		this.users = new ArrayList<User>();
		this.articles = new ArrayList<Work>();
	}
	
	public boolean addUser (User user) {
		return this.users.add(user);
	}
	
	public boolean removeUser (User user) {
		return this.users.remove(user);
	}

	public boolean addArticle (Work article) {
		return this.articles.add(article);
	}

	public boolean removeArticle (Work article) {
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

	public List<Work> getArticles() {
		return articles;
	}

	public void setArticles(List<Work> articles) {
		this.articles = articles;
	}
}
