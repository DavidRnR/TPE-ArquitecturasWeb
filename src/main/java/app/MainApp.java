package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Article;
import entities.Articulo;
import entities.Category;
import entities.KeyWord;
import entities.Poster;
import entities.Resumen;
import entities.Role;
import entities.User;
import services.ArticleDAO;
import services.CategoryDAO;
import services.KeyWordDAO;
import services.RoleDAO;
import services.UserDAO;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

public class MainApp {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cacic2018");
		EntityManager em = emf.createEntityManager();

		Role roleAutor = new Role("autor");
		Role roleEvaluador = new Role("evaluador");

		KeyWord keyword1 = new KeyWord("Machine Learning");
		KeyWord keyword2 = new KeyWord("IOT");
		KeyWord keyword3 = new KeyWord("Seguridad");
		KeyWord keyword4 = new KeyWord("Red");
		KeyWord keyword5 = new KeyWord("Javascript");
		KeyWord keyword6 = new KeyWord("Python");
		KeyWord keyword7 = new KeyWord("Micro Servicios");
		KeyWord keyword8 = new KeyWord("Arquitectura");
	

		User user1 = new User("David", "Martin", "carlosdavidmartin@gmail.com");
		user1.addRole(roleAutor);
		user1.addRole(roleEvaluador);
		User user2 = new User("Emilio", "Martin", "emiliomartin@gmail.com");
		user2.addRole(roleAutor);
		User user3 = new User("Andrea", "Perez", "andreaperez@hotmail.com");
		user3.addRole(roleAutor);
		User user4 = new User("Jorge", "Gomez", "gomezjorge@yahoo.com");
		user4.addRole(roleAutor);
		user4.addRole(roleEvaluador);
		User user5 = new User("Carolina", "Ferrari", "ferraricaro@hotmail.com");
		user5.addRole(roleEvaluador);
		User user6 = new User("Camila", "Martinez", "camilamart@gmail.com");
		user6.addRole(roleAutor);
		User user7 = new User("Pedro", "Monzon", "monpedro@yahoo.com");
		user7.addRole(roleAutor);
		User user8 = new User("Esteban", "Garcia", "estgarcia@gmail.com");
		user8.addRole(roleAutor);
		User user9 = new User("Paola", "Mendez", "pao_mendez@gmail.com");
		user9.addRole(roleAutor);
		User user10 = new User("Victoria", "Pozo", "pozovicky@gmail.com");
		user10.addRole(roleAutor);
		// Evaluadores
		user1.addKeyWord(keyword1);
		user1.addKeyWord(keyword2);
		user1.addKeyWord(keyword3);
		user1.addKeyWord(keyword4);
		user1.addKeyWord(keyword5);
		user1.addKeyWord(keyword6);
		user1.addKeyWord(keyword7);
		user1.addKeyWord(keyword8);
		user1.setExpert(true);

		user4.addKeyWord(keyword5);

		user5.addKeyWord(keyword5);
		user5.addKeyWord(keyword6);


		Category category1 = new Articulo("Artículo");
		Category category2 = new Resumen("Resumen");
		Category category3 = new Poster("Poster");
	

		Calendar cal = Calendar.getInstance();


		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateArt1 = cal.getTime();
		Article article1 = new Article("Mas allá de la gran Network", category1, dateArt1);
		article1.addKeyWord(keyword4);
		article1.addKeyWord(keyword3);
//		article1.addAuthor(user2);
//		user2.addArticle(article1);

		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt2 = cal.getTime();
		Article article2 = new Article("Micro Servicios y algo mas", category3, dateArt2);
		article2.addKeyWord(keyword7);
		article2.addKeyWord(keyword8);
//		article2.addAuthor(user3);
//		user3.addArticle(article2);
	
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateArt3 = cal.getTime();
		Article article3 = new Article("Aprendamos Internet de las Cosas", category2, dateArt3);
		article3.addKeyWord(keyword2);
//		article3.addAuthor(user9);
//		user9.addArticle(article3);
	
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt4 = cal.getTime();
		Article article4 = new Article("IOT para principantes", category2, dateArt4);
		article4.addKeyWord(keyword2);
//		article3.addAuthor(user6);
//		user6.addArticle(article3);
		
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		Date dateArt5 = cal.getTime();
		Article article5 = new Article("Machine Learning básico", category2, dateArt5);
		article5.addKeyWord(keyword1);
//		article5.addAuthor(user4);
//		user4.addArticle(article5);
		
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 18);
		Date dateArt6 = cal.getTime();
		Article article6 = new Article("Python y nada más", category3, dateArt6);
		article6.addKeyWord(keyword6);
//		article6.addAuthor(user2);
//		user2.addArticle(article6);
		
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.DAY_OF_MONTH, 22);
		Date dateArt7 = cal.getTime();
		Article article7 = new Article("Javascript para principiantes", category3, dateArt7);
		article7.addKeyWord(keyword5);
//		article7.addAuthor(user1);
//		user1.addArticle(article7);
		
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		Date dateArt8 = cal.getTime();
		Article article8 = new Article("Seguridad Informática", category1, dateArt8);
		article8.addKeyWord(keyword3);
		article8.addKeyWord(keyword4);
//		article8.addAuthor(user10);
//		user10.addArticle(article8);
		
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 29);
		Date dateArt9 = cal.getTime();
		Article article9 = new Article("Como armar una Red", category1, dateArt9);
		article9.addKeyWord(keyword4);
//		article9.addAuthor(user9);
//		user9.addArticle(article9);
				
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 14);
		Date dateArt10 = cal.getTime();
		Article article10 = new Article("Por qué Micro Servicios?", category3, dateArt10);
		article10.addKeyWord(keyword7);
		article10.addKeyWord(keyword8);
//		article10.addAuthor(user8);
//		user8.addArticle(article10);

		RoleDAO.getInstance().persist(roleAutor, em); 
		RoleDAO.getInstance().persist(roleEvaluador, em);
		KeyWordDAO.getInstance().persist(keyword1, em);
		KeyWordDAO.getInstance().persist(keyword2, em);
		KeyWordDAO.getInstance().persist(keyword3, em);
		KeyWordDAO.getInstance().persist(keyword4, em);
		KeyWordDAO.getInstance().persist(keyword5, em);
		KeyWordDAO.getInstance().persist(keyword6, em);
		KeyWordDAO.getInstance().persist(keyword7, em);
		KeyWordDAO.getInstance().persist(keyword8, em);
		
		CategoryDAO.getInstance().persist(category1, em);
		CategoryDAO.getInstance().persist(category2, em);
		CategoryDAO.getInstance().persist(category3, em);
				
		UserDAO.getInstance().persist(user1, em);
		UserDAO.getInstance().persist(user2, em);
		UserDAO.getInstance().persist(user3, em);
		UserDAO.getInstance().persist(user4, em);
		UserDAO.getInstance().persist(user5, em);
		UserDAO.getInstance().persist(user6, em);
		UserDAO.getInstance().persist(user7, em);
		UserDAO.getInstance().persist(user8, em);
		UserDAO.getInstance().persist(user9, em);
		UserDAO.getInstance().persist(user10, em);
		
		ArticleDAO.getInstance().persist(article1, em);
		ArticleDAO.getInstance().persist(article2, em);
		ArticleDAO.getInstance().persist(article3, em);
		ArticleDAO.getInstance().persist(article4, em);
		ArticleDAO.getInstance().persist(article5, em);
		ArticleDAO.getInstance().persist(article6, em);
		ArticleDAO.getInstance().persist(article7, em);
		ArticleDAO.getInstance().persist(article8, em);
		ArticleDAO.getInstance().persist(article9, em);
		ArticleDAO.getInstance().persist(article10, em);
		
		em.close();
		emf.close();
	}
}
