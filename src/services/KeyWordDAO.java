package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.KeyWord;

public class KeyWordDAO implements DAO<KeyWord,Integer> {

	private static KeyWordDAO daoKeyWord;

	private KeyWordDAO(){}{

	}

	public static KeyWordDAO getInstance() {
		if(daoKeyWord == null)
			daoKeyWord = new KeyWordDAO();
		return daoKeyWord;
	}

	@Override
	public KeyWord persist(KeyWord keyword) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(keyword);
		entityManager.getTransaction().commit();
		entityManager.close();
		return keyword;
	}

	@Override
	public KeyWord update(Integer id, KeyWord keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyWord findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		KeyWord keyword = entityManager.find(KeyWord.class, id);
		return keyword;
	}

	@Override
	public List<KeyWord> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(KeyWord.FIND_ALL);
		List<KeyWord> keywords = q.getResultList();
		return keywords;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
}
