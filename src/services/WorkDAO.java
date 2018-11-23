package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import entities.Work;

public class WorkDAO implements DAO<Work,Integer> {

	private static WorkDAO daoWork;

	private WorkDAO(){}{

	}

	public static WorkDAO getInstance() {
		if(daoWork == null)
			daoWork = new WorkDAO();
		return daoWork;
	}

	@Override
	public Work persist(Work work) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(work);
		entityManager.getTransaction().commit();
		entityManager.close();
		return work;
	}

	@Override
	public Work update(Integer id, Work work) {
		EntityManager entityManager = EMF.createEntityManager();
		Work wo = entityManager.find(Work.class, id);
		
		if(wo != null) {
			entityManager.getTransaction().begin();
			entityManager.persist(work);
			entityManager.getTransaction().commit();
			entityManager.close();
			return work;
		}
		return null;
	}
	
	public Work updateREST (Integer id, Work work) {
		EntityManager entityManager = EMF.createEntityManager();
		Work wo = entityManager.find(Work.class, id);
		
		if(wo != null) {
			entityManager.getTransaction().begin();
			entityManager.merge(work);
			entityManager.getTransaction().commit();
			entityManager.close();
			return work;
		}
		return null;
	}

	@Override
	public Work findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Work work = entityManager.find(Work.class, id);
		return work;
	}

	@Override
	public List<Work> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(Work.FIND_ALL);
		List<Work> works = q.getResultList();
		return works;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Work findByName(String name) {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(Work.FIND_BY_NAME);
		q.setParameter(1, name);
		return (Work) q.getSingleResult();
	}
	
	/**
	 * Obtener todos los Trabajos que todavia NO han sido revisados
	 * @param entityManager
	 * @return
	 */
	public List<Work> findAllNoReviewed() {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(Work.FIND_ALL_NO_REVIEWED);
		List<Work> works = q.getResultList();
		return works;
	}
	
	/**
	 * Obtener todos los Trabajos que han sido revisados
	 * @param entityManager
	 * @return
	 */
	public List<Work> findAllReviewed() {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(Work.FIND_ALL_REVIEWED);
		List<Work> works = q.getResultList();
		return works;
	}
	
}
