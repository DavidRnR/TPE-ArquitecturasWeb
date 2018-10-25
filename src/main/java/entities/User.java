package entities;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = User.FIND_ALL, query="SELECT u FROM User u")
@NamedQuery(name = User.FIND_BY_ID, query="SELECT u FROM User u WHERE u.id = ?1")
@NamedQuery(name = User.FIND_BY_DNI, query="SELECT u FROM User u WHERE u.dni = ?1")
@NamedQuery(name = User.FIND_BY_EMAIL, query="SELECT u FROM User u WHERE u.email = ?1")
@NamedQuery(name = User.FIND_EVALUADORES, query="SELECT u FROM User u JOIN u.roles r WHERE r.name = 'evaluador'") 
@NamedQuery(name = User.DELETE_TABLE, query="DELETE FROM User u") 

@Entity
@Table(name="User")
public class User {

	public static final String FIND_ALL = "User.findAll";
	public static final String FIND_BY_ID = "User.findById";
	public static final String FIND_BY_DNI = "User.findByDni";
	public static final String FIND_BY_EMAIL = "User.findByEmail";
	public static final String FIND_EVALUADORES = "User.findEvaluadores";
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
	private List<Role> roles;
	@ManyToMany
	private List<KeyWord> keyWords;
	@ManyToMany
	private List<Work> works;
	@ManyToMany
	@JoinTable(name = "User_Rev_Work")
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
	
	public int getId() {
		return this.id;
	}
	
	public long getDni() {
		return this.dni;
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
	
	public boolean canReviewArticle (Work work) {

		if(isEvaluador() && !isAuthor(work) && this.worksRev.size() <= MAX_ART_REV && work.containsKeyWordsNeeded(keyWords)) {
			return true;
		}


		return false;
	}
	
	public boolean isEvaluador () {

		for (Role role : roles) {

			if(role.isEvaluador()) {
				return true;
			}

		}
		return false;
	}
	
	public boolean isAuthor (Work work) {
		
		if(this.works.contains(work)) {
			return true;
		}
		return false;
	}
}
