package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Article;
import entities.Category;
import entities.KeyWord;
import entities.Role;
import entities.User;
import services.ArticleDAO;
import services.CategoryDAO;
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
		RoleDAO.getInstance().persist(roleAutor, em);
		Role roleEvaluador = new Role("evaluador");
		RoleDAO.getInstance().persist(roleEvaluador, em);
		
		User user1 = new User("David", "Martín", "carlosdavidmartin@gmail.com");
		User user2 = new User("Emilio", "Martín", "emiliomartin@gmail.com");
		User user3 = new User("Andrea", "Pérez", "andreaperez@hotmail.com");
		User user4 = new User("Jorge", "Gómez", "gomezjorge@yahoo.com");
		User user5 = new User("Carolina", "Ferrari", "ferraricaro@hotmail.com");
		User user6 = new User("Camila", "Martinez", "camilamart@gmail.com");
		User user7 = new User("Pedro", "Monzon", "monpedro@yahoo.com");
		User user8 = new User("Esteban", "García", "estgarcia@gmail.com");
		User user9 = new User("Paola", "Mendez", "pao_mendez@gmail.com");
		User user10 = new User("Victoria", "Pozo", "pozovicky@gmail.com");
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
		
		Category category1 = new Category("Redes");
		Category category2 = new Category("IOT");
		Category category3 = new Category("Micro Servicios");
		Category category4 = new Category("AI");
		CategoryDAO.getInstance().persist(category1, em);
		CategoryDAO.getInstance().persist(category2, em);
		CategoryDAO.getInstance().persist(category3, em);
		CategoryDAO.getInstance().persist(category4, em);
		
		
		Calendar cal = Calendar.getInstance();
		
		
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateArt1 = cal.getTime();
		Article article1 = new Article("Mas allá de la gran Network", category1, dateArt1);
		ArticleDAO.getInstance().persist(article1, em);
		
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt2 = cal.getTime();
		Article article2 = new Article("Micro Servicios y algo mas", category3, dateArt2);
		ArticleDAO.getInstance().persist(article2, em);
		
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateArt3 = cal.getTime();
		Article article3 = new Article("Aprendamos Internet de las Cosas", category2, dateArt3);
		ArticleDAO.getInstance().persist(article3, em);
		
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt4 = cal.getTime();
		Article article4 = new Article("IOT para principantes", category2, dateArt4);
		ArticleDAO.getInstance().persist(article4, em);
		
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		Date dateArt5 = cal.getTime();
		Article article5 = new Article("Machine Learning básico", category4, dateArt5);
		ArticleDAO.getInstance().persist(article5, em);
		
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 18);
		Date dateArt6 = cal.getTime();
		Article article6 = new Article("Machine Learning avanzado", category4, dateArt6);
		ArticleDAO.getInstance().persist(article6, em);
		
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.DAY_OF_MONTH, 22);
		Date dateArt7 = cal.getTime();
		Article article7 = new Article("Machine Learning avanzado 2", category4, dateArt7);
		ArticleDAO.getInstance().persist(article7, em);
		
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		Date dateArt8 = cal.getTime();
		Article article8 = new Article("Seguridad Informática", category1, dateArt8);
		ArticleDAO.getInstance().persist(article8, em);
		
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 29);
		Date dateArt9 = cal.getTime();
		Article article9 = new Article("Como armar una Red", category1, dateArt9);
		ArticleDAO.getInstance().persist(article9, em);
		
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 14);
		Date dateArt10 = cal.getTime();
		Article article10 = new Article("Por qué Micro Servicios?", category3, dateArt1);
		ArticleDAO.getInstance().persist(article10, em);
		
//		KeyWord keyword1 = new KeyWord("");
//		KeyWord keyword2 = new KeyWord();
//		KeyWord keyword3 = new KeyWord();
//		KeyWord keyword4 = new KeyWord();
//		KeyWord keyword5 = new KeyWord();
//		KeyWord keyword6 = new KeyWord();
		
		em.close();
		emf.close();
	}
}
