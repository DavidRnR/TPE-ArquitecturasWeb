package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQuery(name = User.FIND_ALL, query="SELECT u FROM User u")
@NamedQuery(name = User.FIND_BY_ID, query="SELECT u FROM User u WHERE u.id = ?1")
@NamedQuery(name = User.FIND_BY_DNI, query="SELECT u FROM User u WHERE u.dni = ?1")
@NamedQuery(name = User.FIND_BY_EMAIL, query="SELECT u FROM User u WHERE u.email = ?1")
@NamedQuery(name = User.FIND_EVALUADORES, query="SELECT u FROM User u JOIN u.roles r WHERE r.name = 'evaluador'")
@NamedQuery(name = User.FIND_BY_USER_AND_KEYWORD, query="SELECT w FROM User u JOIN u.keyWords k JOIN u.works w WHERE u.dni = ?1 AND k.word = ?2")
@NamedQuery(name = User.DELETE_TABLE, query="DELETE FROM User u") 

@Entity
@Table(name="user")
public class User {

	public static final String FIND_ALL = "User.findAll";
	public static final String FIND_BY_ID = "User.findById";
	public static final String FIND_BY_DNI = "User.findByDni";
	public static final String FIND_BY_EMAIL = "User.findByEmail";
	public static final String FIND_EVALUADORES = "User.findEvaluadores";
	public static final String FIND_BY_USER_AND_KEYWORD = "User.findByUserAndKeyWord";
	public static final String DELETE_TABLE = "User.deleteTable";

	private static final int MAX_ART_REV = 3;

	@Id 
	@GeneratedValue
	private int id;
	private long dni;
	private String first_name;
	private String last_name;
	private String email;
	private boolean expert;

	@ManyToMany 
	@JoinTable(name = "user_role")
	private List<Role> roles;
	@ManyToMany
	@JoinTable(name = "user_keyword",
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "keyword_id"))
	private List<KeyWord> keyWords;
	@ManyToMany
	@JoinTable(name = "user_work",
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "work_id"))
	private List<Work> works;
	@ManyToMany
	@JoinTable(name = "user_rev_work",
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "workrev_id"))
	private List<Work> worksRev;

	public User() {}

	public User(int dni, String first_name, String last_name, String email) {
		this.dni = dni;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.expert = false;
		this.roles = new ArrayList<Role>();
		this.works = new ArrayList<Work>();
		this.keyWords = new ArrayList<KeyWord>();
		this.worksRev = new ArrayList<Work>();
	}

	public boolean addRole (Role role) {
		return this.roles.add(role);
	}

	public boolean removeRole (Role role) {
		return this.roles.remove(role);
	}

	public boolean addWork (Work work) {
		return this.works.add(work);
	}

	public boolean removeWork (Work work) {
		return this.works.remove(work);
	}

	public boolean addWorkRev (Work work) {
		if(canReviewArticle(work)) {
			this.worksRev.add(work);
			return true;
		}
		return false;
	}

	public boolean removeWorkRev (Work work) {
		return this.worksRev.remove(work);
	}

	public boolean addKeyWord (KeyWord keyWord) {
		if(isEvaluador()) {
			return this.keyWords.add(keyWord);
		}
		return false;
	}

	public boolean removeKeyWord (KeyWord keyWord) {
		return this.keyWords.remove(keyWord);
	}

	public boolean setWorkAsReviewed (Work work, Date reviewed) {
		if(this.worksRev.contains(work)) {
			work.setReviewed(reviewed);
			return true;
		}
		return false;
	}

	public int getId() {
		return this.id;
	}

	public long getDni() {
		return this.dni;
	}

	public void setDni(long dni) {
		this.dni = dni;
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

	public List<Work> getWorks() {
		return this.works;
	}

	public List<Work> getWorksRev() {
		return this.worksRev;
	}

	@Override
	public String toString () {
		return this.getFirst_name() + " " + this.getLast_name() + "\n" + 
				"Email: " + this.email +  "\n" +
				"Areas: " + this.keyWords + "\n" + 
				"Roles: " + this.roles + "\n" + 
				"Articulos: " + this.works + "\n" + 
				"Articulos para Revisar: " + this.worksRev +  "\n"  +
				"Experto: " + this.expert;

	}

	@JsonIgnore
	public boolean canReviewArticle (Work work) {
		List<Work> worksInReview = getWorksInReview();

		if(isEvaluador() && !isAuthorByWork(work) && worksInReview.size() <= MAX_ART_REV && work.containsKeyWordsNeeded(keyWords)) {
			return true;
		}


		return false;
	}
	@JsonIgnore
	public boolean isEvaluador () {

		for (Role role : roles) {

			if(role.isEvaluador()) {
				return true;
			}

		}
		return false;
	}
	@JsonIgnore
	public boolean isAuthor () {

		for (Role role : roles) {

			if(role.isAutor()) {
				return true;
			}

		}
		return false;
	}

	@JsonIgnore
	public boolean isAuthorByWork (Work work) {

		if(this.works.contains(work)) {
			return true;
		}
		return false;
	}

	@JsonIgnore
	public List<Work> getWorksInReview () {
		List<Work> worksInReview = new ArrayList<Work>();

		for (Work work : this.worksRev) {
			if(work.getReviewed() != null) {
				worksInReview.add(work);
			}
		}
		return worksInReview;
	}
}
