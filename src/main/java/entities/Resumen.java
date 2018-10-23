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
	public boolean containsKeyWordsNeeded(List<KeyWord> artKw, List<KeyWord> userKw) {
		for (KeyWord keyWord : userKw) {
			if(!artKw.contains(keyWord)) {
				return false;
			}
		}
		return true;
	}
}
