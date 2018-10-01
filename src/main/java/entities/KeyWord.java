package entities;

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
	}
}
