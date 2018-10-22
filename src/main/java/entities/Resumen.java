package entities;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Resumen extends Category{
	public Resumen() {}
	public Resumen (String name) {
		super(name);
	}

	@Override
	public boolean containsKeyWords(List<KeyWord> artKw, List<KeyWord> userKw) {
		for (KeyWord keyWord : userKw) {
			if(!artKw.contains(keyWord.getWord())) {
				return false;
			}
		}
		return true;
	}
}
