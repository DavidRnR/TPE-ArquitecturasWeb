package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Article;

public class ArticleDAO implements DAO<Article,Integer> {

	private static ArticleDAO daoArticle;

	private ArticleDAO(){}{

	}

	public static ArticleDAO getInstance() {
		if(daoArticle == null)
			daoArticle = new ArticleDAO();
		return daoArticle;
	}

	@Override
	public Article persist(Article article, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist(article);
		entityManager.getTransaction().commit();

		return article;
	}

	@Override
	public Article update(Integer id, Article article, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article findById(Integer id, EntityManager entityManager) {
		Article article = entityManager.find(Article.class, id);
		return article;
	}

	@Override
	public List<Article> findAll(EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(Article.FIND_ALL);
		List<Article> articles = q.getResultList();
		return articles;
	}

	@Override
	public boolean delete(Integer id, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Article findByName(String name, EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(Article.FIND_BY_NAME);
		q.setParameter(1, name);
		return (Article) q.getSingleResult();
	}
}
