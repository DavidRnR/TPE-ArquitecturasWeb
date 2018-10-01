package services;

import javax.persistence.EntityManager;

import entities.Category;

public class CacicService {
	
	public static void createCategory(String name,EntityManager em) {
		em.getTransaction( ).begin( );	
		Category category = new Category(name);
		em.persist(category);
		em.getTransaction().commit();
	}
}
