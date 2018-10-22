package entities;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Articulo extends Category {
	public Articulo() {}
	public Articulo (String name) {
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