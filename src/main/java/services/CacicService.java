package services;

import java.util.Date;

import javax.persistence.EntityManager;

import entities.Article;
import entities.Category;
import entities.Role;
import entities.User;

public class CacicService {
	
	public static void createCategory(String name,EntityManager em) {
		em.getTransaction( ).begin( );	
		Category category = new Category(name);
		em.persist(category);
		em.getTransaction().commit();
	}
	
	public static void createRole(String name,EntityManager em) {
		em.getTransaction( ).begin( );	
		Role role = new Role(name);
		em.persist(role);
		em.getTransaction().commit();
	}
	
	public static void createUser(String firstName,String lastName, String email, EntityManager em) {
		em.getTransaction( ).begin( );	
		User user = new User(firstName, lastName, email);
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public static void createArticle(String name,Category category, Date created, EntityManager em) {
		em.getTransaction( ).begin( );	
		Article article = new Article(name, category, created);
		em.persist(article);
		em.getTransaction().commit();
	}
}
