package entities;

import java.util.List;

public class Poster extends Category{
	public Poster (String name) {
		super(name);
	}

	@Override
	public boolean containsKeyWords(List<KeyWord> artKw, List<KeyWord> userKw) {
		for (KeyWord keyWord : userKw) {
			if(artKw.contains(keyWord.getWord())) {
				return true;
			}
		}
		return false;
	}
}
