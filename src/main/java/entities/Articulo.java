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
	public boolean containsKeyWordsNeeded(List<KeyWord> artKw, List<KeyWord> userKw) {
		for (KeyWord keyWord : artKw) {
			if(!userKw.contains(keyWord)) {
				return false;
			}
		}
		return true;
	}
}
