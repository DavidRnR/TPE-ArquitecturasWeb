package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Article;
import entities.User;

public class UserDAO implements DAO<User,Integer>{

	private static UserDAO daoUser;

	private UserDAO(){}{

	}

	public static UserDAO getInstance() {
		if(daoUser == null)
			daoUser = new UserDAO();
		return daoUser;
	}

	@Override
	public User persist(User user, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();

		return user;
	}

	@Override
	public User update(Integer id, User user, EntityManager entityManager) {
		User us = entityManager.find(User.class, id);
		
		if(us != null) {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return user;
		}
		
		return null;
	}

	@Override
	public User findById(Integer id, EntityManager entityManager) {
		User user = entityManager.find(User.class, id);
		return user;
	}

	@Override
	public List<User> findAll(EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(User.FIND_ALL);
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public boolean delete(Integer id, EntityManager entityManager) {
		User user = this.findById(id, entityManager);
		if(user != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(user);
			entityManager.getTransaction().commit();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addArticle (Integer id, Article article, EntityManager entityManager) {
		User user = this.findById(id, entityManager);
		if(user != null) {
			entityManager.getTransaction().begin();
			user.addArticle(article);
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<User> getEvaludoares (EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(User.FIND_EVALUADORES);
		List<User> users = q.getResultList();
		return users;
	}
	
	public User findByDni(long dni, EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(User.FIND_BY_DNI);
		q.setParameter(1, dni);
		return (User) q.getSingleResult();
	}
}