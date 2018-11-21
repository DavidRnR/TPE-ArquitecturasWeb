package services;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Category;
import entities.User;

import java.util.List;

public class CategoryDAO implements DAO<Category, Integer> {

	private static CategoryDAO daoCategory;

	public CategoryDAO() {} 


	public static CategoryDAO getInstance() {
		if(daoCategory == null)
			daoCategory = new CategoryDAO();
		return daoCategory;
	}


	@Override
	public Category persist(Category category) {
		EntityManager entityManager = EMF.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(category);
		entityManager.getTransaction().commit();
		entityManager.close();
		return category;
	}


	@Override
	public Category update(Integer id, Category category) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Category findById(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Category category = entityManager.find(Category.class, id);
		entityManager.close();
		return category;
	}


	@Override
	public List<Category> findAll() {
		EntityManager entityManager = EMF.createEntityManager();
		Query q = entityManager.createNamedQuery(Category.FIND_ALL);
		List<Category> categories = q.getResultList();
		return categories;
	}


	@Override
	public boolean delete(Integer id) {
		EntityManager entityManager = EMF.createEntityManager();
		Category category = this.findById(id);
		if(category != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(category);
			entityManager.getTransaction().commit();
			entityManager.close();
			return true;
		}
		else {
			return false;
		}
	}

}
