package entities;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Poster extends Category{
	public Poster() {}
	public Poster (String name) {
		super(name);
	}

	@Override
	public boolean containsKeyWordsNeeded(List<KeyWord> artKw, List<KeyWord> userKw) {
		for (KeyWord keyWord : userKw) {
			if(artKw.contains(keyWord)) {
				return true;
			}
		}
		return false;
	}
}
