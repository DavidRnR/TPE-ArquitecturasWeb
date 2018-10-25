package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Work;
import entities.Articulo;
import entities.Category;
import entities.KeyWord;
import entities.Poster;
import entities.Resumen;
import entities.Role;
import entities.User;

import services.WorkDAO;
import services.CacicService;
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


		Category category1 = new Articulo("Articulo");
		Category category2 = new Resumen("Resumen");
		Category category3 = new Poster("Poster");
	

		Calendar cal = Calendar.getInstance();


		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateArt1 = cal.getTime();
		Work article1 = new Work("Mas alla de la gran Network", category1, dateArt1);
		article1.addKeyWord(keyword4);
		article1.addKeyWord(keyword3);

		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt2 = cal.getTime();
		Work article2 = new Work("Micro Servicios y algo mas", category3, dateArt2);
		article2.addKeyWord(keyword7);
		article2.addKeyWord(keyword8);
	
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateArt3 = cal.getTime();
		Work article3 = new Work("Aprendamos Internet de las Cosas", category2, dateArt3);
		article3.addKeyWord(keyword2);
	
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt4 = cal.getTime();
		Work article4 = new Work("IOT para principantes", category2, dateArt4);
		article4.addKeyWord(keyword2);
		
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		Date dateArt5 = cal.getTime();
		Work article5 = new Work("Machine Learning basico", category2, dateArt5);
		article5.addKeyWord(keyword1);
		
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 18);
		Date dateArt6 = cal.getTime();
		Work article6 = new Work("Python y nada mas", category3, dateArt6);
		article6.addKeyWord(keyword6);
		
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.DAY_OF_MONTH, 22);
		Date dateArt7 = cal.getTime();
		Work article7 = new Work("Javascript para principiantes", category3, dateArt7);
		article7.addKeyWord(keyword5);
		
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		Date dateArt8 = cal.getTime();
		Work article8 = new Work("Seguridad Informatica", category1, dateArt8);
		article8.addKeyWord(keyword3);
		article8.addKeyWord(keyword4);
		
		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 29);
		Date dateArt9 = cal.getTime();
		Work article9 = new Work("Como armar una Red", category1, dateArt9);
		article9.addKeyWord(keyword4);
				
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 14);
		Date dateArt10 = cal.getTime();
		Work article10 = new Work("Por que Micro Servicios?", category3, dateArt10);
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
		
		WorkDAO.getInstance().persist(article1, em);
		WorkDAO.getInstance().persist(article2, em);
		WorkDAO.getInstance().persist(article3, em);
		WorkDAO.getInstance().persist(article4, em);
		WorkDAO.getInstance().persist(article5, em);
		WorkDAO.getInstance().persist(article6, em);
		WorkDAO.getInstance().persist(article7, em);
		WorkDAO.getInstance().persist(article8, em);
		WorkDAO.getInstance().persist(article9, em);
		WorkDAO.getInstance().persist(article10, em);
		
		// Autores
		user1.addWork(article7);
		user2.addWork(article1);
		user2.addWork(article6);
		user3.addWork(article2);
		user4.addWork(article5);
		user6.addWork(article3);
		user8.addWork(article10);
		user9.addWork(article3);
		user9.addWork(article9);
		user10.addWork(article8);
		
		user1.addWorkRev(article9);
		
		UserDAO.getInstance().update(user1.getId(), user1, em);
		UserDAO.getInstance().update(user2.getId(), user2, em);
		UserDAO.getInstance().update(user3.getId(), user3, em);
		UserDAO.getInstance().update(user4.getId(), user4, em);
		UserDAO.getInstance().update(user6.getId(), user6, em);
		UserDAO.getInstance().update(user8.getId(), user8, em);
		UserDAO.getInstance().update(user9.getId(), user9, em);
		UserDAO.getInstance().update(user10.getId(), user10, em);
		
//		System.out.println(user1.addArticleRev(article7));
//		System.out.println("Roles:" + user6.getRoles().toString());
//		System.out.println("User 6 puede?" + user6.addArticleRev(article9));
//		System.out.println(user5.addArticleRev(article7));
//		System.out.println(CacicService.findEvaluadores("Javascript para principiantes", em));
//		System.out.println(CacicService.findArticlesToEvaluador(31156181, em));
		System.out.println(CacicService.getArticlesByEvaluador(31156181, em));
		em.close();
		emf.close();
	}
}
