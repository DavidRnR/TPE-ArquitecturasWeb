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
		entityManager.close();
		return user;
	}

	@Override
	public User update(Integer id, User user) {
		EntityManager entityManager = EMF.createEntityManager();
				 
		User us = entityManager.find(User.class, id);
		
		if(us != null) {
			entityManager.getTransaction().begin();
			entityManager.merge(user);
			entityManager.getTransaction().commit();
			entityManager.close();
			return user;
		}
		
		return null;
	}

	public User updateREST (Integer id, User user) {
		EntityManager entityManager = EMF.createEntityManager();
				 
		User us = entityManager.find(User.class, id);
		
		if(us != null) {
			entityManager.getTransaction().begin();
			if(user.getFirst_name() != null && user.getFirst_name() != "") {
				us.setFirst_name(user.getFirst_name());				
			}
			if(user.getLast_name() != null && user.getLast_name() != "") {
				us.setLast_name(user.getLast_name());			
			}
		
			entityManager.merge(us);
			entityManager.getTransaction().commit();
			entityManager.close();
			return us;
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
		entityManager.close();
		return users;
	}

	@Override
	public boolean delete(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(User.class, id));
		entityManager.getTransaction().commit();
		User user = entityManager.find(User.class, id);
		entityManager.close();


		if(user == null) {
			return true;

		}

		return false;
	}

	public boolean addWork (long dni, Work work) {
		EntityManager entityManager = EMF.createEntityManager();
		User user = this.findByDni(dni);
		if(user != null) {
			entityManager.getTransaction().begin();
			user.addWork(work);
			entityManager.persist(user);
			entityManager.getTransaction().commit();
			entityManager.close();
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
			entityManager.close();
			return true;
		}
		else {
			return false;
		}
	}

	public List<User> getEvaluadores () {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(User.FIND_EVALUADORES);
		List<User> users = q.getResultList();
		entityManager.close();
		return users;
	}

	public User findByDni(long dni) {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(User.FIND_BY_DNI);
		q.setParameter(1, dni);
		User user = (User) q.getResultList().stream().findFirst().orElse(null);
		return user;
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