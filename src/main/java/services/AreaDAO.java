package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Area;

public class AreaDAO implements DAO<Area,Integer> {

	private static AreaDAO daoArea;

	private AreaDAO(){}{

	}

	public static AreaDAO getInstance() {
		if(daoArea == null)
			daoArea = new AreaDAO();
		return daoArea;
	}

	@Override
	public Area persist(Area area, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist(area);
		entityManager.getTransaction().commit();

		return area;
	}

	@Override
	public Area update(Integer id, Area area, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Area findById(Integer id, EntityManager entityManager) {
		Area areas = entityManager.find(Area.class, id);
		return areas;
	}

	@Override
	public List<Area> findAll(EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(Area.FIND_ALL);
		List<Area> areas = q.getResultList();
		return areas;
	}

	@Override
	public boolean delete(Integer id, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return false;
	}
}
