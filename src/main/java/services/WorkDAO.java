package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Work;

public class WorkDAO implements DAO<Work,Integer> {

	private static WorkDAO daoArticle;

	private WorkDAO(){}{

	}

	public static WorkDAO getInstance() {
		if(daoArticle == null)
			daoArticle = new WorkDAO();
		return daoArticle;
	}

	@Override
	public Work persist(Work article, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist(article);
		entityManager.getTransaction().commit();

		return article;
	}

	@Override
	public Work update(Integer id, Work article, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Work findById(Integer id, EntityManager entityManager) {
		Work article = entityManager.find(Work.class, id);
		return article;
	}

	@Override
	public List<Work> findAll(EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(Work.FIND_ALL);
		List<Work> articles = q.getResultList();
		return articles;
	}

	@Override
	public boolean delete(Integer id, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Work findByName(String name, EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(Work.FIND_BY_NAME);
		q.setParameter(1, name);
		return (Work) q.getSingleResult();
	}
}
