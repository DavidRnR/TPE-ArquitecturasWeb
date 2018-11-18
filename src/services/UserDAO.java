package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Work;
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
	public User persist(User user) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();

		return user;
	}

	@Override
	public User update(Integer id, User user) {
		EntityManager entityManager = EMF.createEntityManager();
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
	public User findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		User user = entityManager.find(User.class, id);
		return user;
	}

	@Override
	public List<User> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(User.FIND_ALL);
		List<User> users = q.getResultList();
		return users;
	}

	@Override
	public boolean delete(Integer dni) {
		EntityManager entityManager = EMF.createEntityManager();
		User user = this.findById(dni);
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
	
	public boolean addWork (long dni, Work work) {
		EntityManager entityManager = EMF.createEntityManager();
		User user = this.findByDni(dni);
		if(user != null) {
			entityManager.getTransaction().begin();
			user.addWork(work);
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addArticleToReview (long dni, Work work) {
		EntityManager entityManager = EMF.createEntityManager();
		User user = this.findByDni(dni);
		if(user != null && user.addWorkRev(work)) {
			entityManager.getTransaction().begin();
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<User> getEvaludoares () {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(User.FIND_EVALUADORES);
		List<User> users = q.getResultList();
		return users;
	}
	
	public User findByDni(long dni) {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(User.FIND_BY_DNI);
		q.setParameter(1, dni);
		return (User) q.getSingleResult();
	}
	
	/**
	 * Obtener todos los Trabajos por Usuario y Palabra Clave (KeyWord)
	 * @param entityManager
	 * @return
	 */
	public List<Work> findWorksByUserAndKeyWord(User user, String kw) {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(User.FIND_BY_USER_AND_KEYWORD);
		q.setParameter(1, user.getDni());
		q.setParameter(2, kw);
		List<Work> works = q.getResultList();
		return works;
	}
}