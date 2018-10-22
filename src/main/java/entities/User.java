package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = User.FIND_ALL, query="SELECT u FROM User u")
@NamedQuery(name = User.FIND_BY_ID, query="SELECT u FROM User u WHERE u.id = ?1")
@NamedQuery(name = User.FIND_BY_EMAIL, query="SELECT u FROM User u WHERE u.email = ?1")

@Entity
@Table(name="User")
public class User {

	public static final String FIND_ALL = "User.findAll";
	public static final String FIND_BY_ID = "User.findById";
	public static final String FIND_BY_EMAIL = "User.findByEmail";

	@Id
	@GeneratedValue
	private int id;
	private String first_name;
	private String last_name;
	private String email;
	private boolean expert;

	@ManyToMany
	private List<Role> roles;
	@ManyToMany
	private List<KeyWord> keyWords;
	@ManyToMany
	private List<Article> articles;
	@ManyToMany
	private List<Article> articlesRev;

	public User() {}

	public User(String first_name, String last_name, String email) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.expert = false;
		this.roles = new ArrayList<Role>();
		this.articles = new ArrayList<Article>();
		this.keyWords = new ArrayList<KeyWord>();
	}

	public boolean addRole (Role role) {
		return this.roles.add(role);
	}

	public boolean removeRole (Role role) {
		return this.roles.remove(role);
	}

	public boolean addArticle (Article article) {
		return this.articles.add(article);
	}

	public boolean removeArticle (Article article) {
		return this.articles.remove(article);
	}

	public boolean addArticleRev (Article article) {
		boolean answer = false;
		if(canReviewArticle(article)) {
			this.articlesRev.add(article);
			article.addReviewer(this);
			answer = true;
		}
		return answer;
	}

	public boolean removeArticleRev (Article article) {
		return this.articlesRev.remove(article);
	}

	public boolean addKeyWord (KeyWord keyWord) {
		return this.keyWords.add(keyWord);
	}

	public boolean removeKeyWord (KeyWord keyWord) {
		return this.keyWords.remove(keyWord);
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isExpert() {
		return this.expert;	
	}

	public void setExpert(boolean exp) {
		this.expert = exp;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<KeyWord> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<KeyWord> keyWords) {
		this.keyWords = keyWords;
	}

	public List<Article> getArticles() {
		return articles;
	}

	//***************************************************************
	private boolean canReviewArticle (Article article) {

		boolean answer = false;

		if(article.containsKeyWords(keyWords) && isEvaluador()) {
			answer = true;
		}


		return answer;
	}

	private boolean isEvaluador () {
		boolean answer = false;
		for (Role role : roles) {

			if(role.isEvaluador()) {
				answer = true;
			}

		}
		return answer;
	}
}
