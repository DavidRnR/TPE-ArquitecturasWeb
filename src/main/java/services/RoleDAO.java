package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Role;

public class RoleDAO implements DAO<Role,Integer> {

	private static RoleDAO daoRole;

	private RoleDAO(){}{

	}

	public static RoleDAO getInstance() {
		if(daoRole == null)
			daoRole = new RoleDAO();
		return daoRole;
	}
	
	@Override
	public Role persist(Role role, EntityManager entityManager) {
		entityManager.getTransaction().begin();
		entityManager.persist(role);
		entityManager.getTransaction().commit();

		return role;
	}

	@Override
	public Role update(Integer id, Role role, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findById(Integer id, EntityManager entityManager) {
		Role role = entityManager.find(Role.class, id);
		return role;
	}

	@Override
	public List<Role> findAll(EntityManager entityManager) {
		Query q = entityManager.createNamedQuery(Role.FIND_ALL);
		List<Role> roles = q.getResultList();
		return roles;
	}

	@Override
	public boolean delete(Integer id, EntityManager entityManager) {
		// TODO Auto-generated method stub
		return false;
	}

}
