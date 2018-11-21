package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@NamedQuery(name = Work.FIND_ALL, query="SELECT w FROM Work w")
@NamedQuery(name = Work.FIND_ALL_REVIEWED, query="SELECT w FROM Work w WHERE w.reviewed != null")
@NamedQuery(name = Work.FIND_ALL_NO_REVIEWED, query="SELECT w FROM Work w WHERE w.reviewed = null")
@NamedQuery(name = Work.FIND_BY_ID, query="SELECT w FROM Work w WHERE w.id = ?1")
@NamedQuery(name = Work.FIND_BY_NAME, query="SELECT w FROM Work w WHERE w.name = ?1")
@NamedQuery(name = Work.DELETE_TABLE, query="DELETE FROM Work w") 

@Entity
@Table(name="work")
public class Work {

	public static final String FIND_ALL = "Work.findAll";
	public static final String FIND_ALL_REVIEWED = "Work.findAllReviewed";
	public static final String FIND_ALL_NO_REVIEWED = "Work.findAllNoReviewed";
	public static final String FIND_BY_ID = "Work.findById";
	public static final String FIND_BY_NAME = "Work.findByName";
	public static final String DELETE_TABLE = "Work.deleteTable";
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Date created;
	private Date reviewed;

	@ManyToOne
	private Category category;
	
	@ManyToMany
	private List<KeyWord> keyWords;
		
	public Work() {}

	public Work(String name, Category category, Date created) {
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
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getReviewed() {
		return this.reviewed;
	}

	public boolean setReviewed(Date reviewed) {
		if(reviewed.after(this.created)) {
			this.reviewed = reviewed;
			return true;
		}
		
		return false;
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
	
	public boolean containsKeyWordsNeeded(List<KeyWord> kw) {
		if(category.containsKeyWordsNeeded(keyWords, kw)) {
			return true;
		}
		return false;
	}
	@Override
	public String toString () {
		return "Nombre: " + this.name + "\n"+ 
				"Creado: " + this.created + "\n"+
				"Revisado: " + this.reviewed + "\n" +
				"Categoria: " + this.category + "\n" +
				"Palabras Clave: " + this.keyWords;
	}
}
