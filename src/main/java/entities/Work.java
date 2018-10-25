package entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = Work.FIND_ALL, query="SELECT w FROM Work w")
@NamedQuery(name = Work.FIND_BY_ID, query="SELECT w FROM Work w WHERE w.id = ?1")
@NamedQuery(name = Work.FIND_BY_NAME, query="SELECT w FROM Work w WHERE w.name = ?1")
@NamedQuery(name = Work.DELETE_TABLE, query="DELETE FROM Work w") 

@Entity
@Table(name="Article")
public class Work {

	public static final String FIND_ALL = "Work.findAll";
	public static final String FIND_BY_ID = "Work.findById";
	public static final String FIND_BY_NAME = "Work.findByName";
	public static final String DELETE_TABLE = "Work.deleteTable";
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private Date created;

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
	
	public boolean containsKeyWordsNeeded(List<KeyWord> kw) {
		if(category.containsKeyWordsNeeded(keyWords, kw)) {
			return true;
		}
		return false;
	}
	@Override
	public String toString () {
		return this.name;
	}
}
