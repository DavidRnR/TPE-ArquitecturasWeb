package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import entities.Article;
import entities.Articulo;
import entities.Category;
import entities.KeyWord;
import entities.Poster;
import entities.Resumen;
import entities.Role;
import entities.User;
import services.ArticleDAO;
import services.CacicService;
import services.CategoryDAO;
import services.KeyWordDAO;
import services.RoleDAO;
import services.UserDAO;

public class ServiceTest {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cacic2018");
	private static EntityManager em = emf.createEntityManager();
	
	@BeforeClass
	public static void setDataTest() {
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


		User user1 = new User(31156181, "David", "Martin", "carlosdavidmartin@gmail.com");
		user1.addRole(roleAutor);
		user1.addRole(roleEvaluador);
		User user2 = new User(30111224, "Emilio", "Martin", "emiliomartin@gmail.com");
		user2.addRole(roleAutor);
		User user3 = new User(28654789, "Andrea", "Perez", "andreaperez@hotmail.com");
		user3.addRole(roleAutor);
		User user4 = new User(6354852, "Jorge", "Gomez", "gomezjorge@yahoo.com");
		user4.addRole(roleAutor);
		user4.addRole(roleEvaluador);
		User user5 = new User(76698471, "Carolina", "Ferrari", "ferraricaro@hotmail.com");
		user5.addRole(roleEvaluador);
		User user6 = new User(33674159, "Camila", "Martinez", "camilamart@gmail.com");
		user6.addRole(roleAutor);
		User user7 = new User(34789654, "Pedro", "Monzon", "monpedro@yahoo.com");
		user7.addRole(roleAutor);
		User user8 = new User(15478951, "Esteban", "Garcia", "estgarcia@gmail.com");
		user8.addRole(roleAutor);
		User user9 = new User(17698473, "Paola", "Mendez", "pao_mendez@gmail.com");
		user9.addRole(roleAutor);
		User user10 = new User(19478963, "Victoria", "Pozo", "pozovicky@gmail.com");
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

		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt2 = cal.getTime();
		Article article2 = new Article("Micro Servicios y algo mas", category3, dateArt2);
		article2.addKeyWord(keyword7);
		article2.addKeyWord(keyword8);

		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateArt3 = cal.getTime();
		Article article3 = new Article("Aprendamos Internet de las Cosas", category2, dateArt3);
		article3.addKeyWord(keyword2);

		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt4 = cal.getTime();
		Article article4 = new Article("IOT para principantes", category2, dateArt4);
		article4.addKeyWord(keyword2);

		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		Date dateArt5 = cal.getTime();
		Article article5 = new Article("Machine Learning básico", category2, dateArt5);
		article5.addKeyWord(keyword1);

		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 18);
		Date dateArt6 = cal.getTime();
		Article article6 = new Article("Python y nada más", category3, dateArt6);
		article6.addKeyWord(keyword6);

		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.DAY_OF_MONTH, 22);
		Date dateArt7 = cal.getTime();
		Article article7 = new Article("Javascript para principiantes", category3, dateArt7);
		article7.addKeyWord(keyword5);

		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		Date dateArt8 = cal.getTime();
		Article article8 = new Article("Seguridad Informática", category1, dateArt8);
		article8.addKeyWord(keyword3);
		article8.addKeyWord(keyword4);

		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 29);
		Date dateArt9 = cal.getTime();
		Article article9 = new Article("Como armar una Red", category1, dateArt9);
		article9.addKeyWord(keyword4);

		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 14);
		Date dateArt10 = cal.getTime();
		Article article10 = new Article("Por qué Micro Servicios?", category3, dateArt10);
		article10.addKeyWord(keyword7);
		article10.addKeyWord(keyword8);

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

		// Autores
		user1.addArticle(article7);
		user2.addArticle(article1);
		user2.addArticle(article6);
		user3.addArticle(article2);
		user4.addArticle(article5);
		user6.addArticle(article3);
		user8.addArticle(article10);
		user9.addArticle(article3);
		user9.addArticle(article9);
		user10.addArticle(article8);

		UserDAO.getInstance().update(user1.getId(), user1, em);
		UserDAO.getInstance().update(user2.getId(), user2, em);
		UserDAO.getInstance().update(user3.getId(), user3, em);
		UserDAO.getInstance().update(user4.getId(), user4, em);
		UserDAO.getInstance().update(user6.getId(), user6, em);
		UserDAO.getInstance().update(user8.getId(), user8, em);
		UserDAO.getInstance().update(user9.getId(), user9, em);
		UserDAO.getInstance().update(user10.getId(), user10, em);
	} 
	
	@AfterClass
	public static void closeEntityManager() {
		
		em.getTransaction( ).begin( );	
		em.createNamedQuery(User.DELETE_TABLE).executeUpdate();
		em.createNamedQuery(Article.DELETE_TABLE).executeUpdate();
		em.createNamedQuery(Category.DELETE_TABLE).executeUpdate();
		em.createNamedQuery(KeyWord.DELETE_TABLE).executeUpdate();
		em.createNamedQuery(Role.DELETE_TABLE).executeUpdate();
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
	@Test
	public void findEvaluadores() {
		List<User> evaluadores = CacicService.findEvaluadores("Javascript para principiantes", em);
		assertTrue("No encuentra evaluadores", !evaluadores.isEmpty());
		
	}
}
