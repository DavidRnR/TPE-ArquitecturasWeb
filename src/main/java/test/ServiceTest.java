package test;

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
		Work work1 = new Work("Mas alla de la gran Network", category1, dateArt1);
		work1.addKeyWord(keyword4);
		work1.addKeyWord(keyword3);

		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt2 = cal.getTime();
		Work work2 = new Work("Micro Servicios y algo mas", category3, dateArt2);
		work2.addKeyWord(keyword7);
		work2.addKeyWord(keyword8);

		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date dateArt3 = cal.getTime();
		Work work3 = new Work("Aprendamos Internet de las Cosas", category2, dateArt3);
		work3.addKeyWord(keyword2);

		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 3);
		Date dateArt4 = cal.getTime();
		Work work4 = new Work("IOT para principantes", category2, dateArt4);
		work4.addKeyWord(keyword2);

		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.MARCH);
		cal.set(Calendar.DAY_OF_MONTH, 6);
		Date dateArt5 = cal.getTime();
		Work work5 = new Work("Machine Learning basico", category2, dateArt5);
		work5.addKeyWord(keyword1);

		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 18);
		Date dateArt6 = cal.getTime();
		Work work6 = new Work("Python y nada mas", category3, dateArt6);
		work6.addKeyWord(keyword6);

		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.DAY_OF_MONTH, 22);
		Date dateArt7 = cal.getTime();
		Work work7 = new Work("Javascript para principiantes", category3, dateArt7);
		work7.addKeyWord(keyword5);

		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 2);
		Date dateArt8 = cal.getTime();
		Work work8 = new Work("Seguridad Informatica", category1, dateArt8);
		work8.addKeyWord(keyword3);
		work8.addKeyWord(keyword4);

		cal.set(Calendar.YEAR, 2017);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 29);
		Date dateArt9 = cal.getTime();
		Work work9 = new Work("Como armar una Red", category1, dateArt9);
		work9.addKeyWord(keyword4);

		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.APRIL);
		cal.set(Calendar.DAY_OF_MONTH, 14);
		Date dateArt10 = cal.getTime();
		Work work10 = new Work("Por que Micro Servicios?", category3, dateArt10);
		work10.addKeyWord(keyword7);
		work10.addKeyWord(keyword8);

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

		WorkDAO.getInstance().persist(work1, em);
		WorkDAO.getInstance().persist(work2, em);
		WorkDAO.getInstance().persist(work3, em);
		WorkDAO.getInstance().persist(work4, em);
		WorkDAO.getInstance().persist(work5, em);
		WorkDAO.getInstance().persist(work6, em);
		WorkDAO.getInstance().persist(work7, em);
		WorkDAO.getInstance().persist(work8, em);
		WorkDAO.getInstance().persist(work9, em);
		WorkDAO.getInstance().persist(work10, em);

		// Autores
		user1.addWork(work7);
		user2.addWork(work1);
		user2.addWork(work6);
		user3.addWork(work2);
		user4.addWork(work5);
		user6.addWork(work3);
		user9.addWork(work3);
		user9.addWork(work9);
		user10.addWork(work8);

		// User 1 evalua article9
		user1.addWorkRev(work9);

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
		em.createNamedQuery(Work.DELETE_TABLE).executeUpdate();
		em.createNamedQuery(Category.DELETE_TABLE).executeUpdate();
		em.createNamedQuery(KeyWord.DELETE_TABLE).executeUpdate();
		em.createNamedQuery(Role.DELETE_TABLE).executeUpdate();
		em.getTransaction().commit();

		em.close();
		emf.close();
	}

	/**
	 * Dado el nombre de un trabajo, devolver todos los posibles Evaluadores
	 */
	@Test
	public void findEvaluadores() {
		List<User> evaluadores = CacicService.findEvaluadores("Javascript para principiantes", em);

		assertTrue("No encuentra evaluadores", !evaluadores.isEmpty() && evaluadores.get(0).getDni() == 6354852);
	}

	/**
	 * Dado un usuario devolver toda su informacion
	 */
	@Test
	public void getUserData() {
		String userData = CacicService.getUserData(31156181, em);
		assertTrue("Usuario no encontrado", userData != null);
	}

	/**
	 * Dado un trabajo devolver toda su informacion
	 */
	@Test
	public void geWorkData() {
		String workData = CacicService.getWorkData("Javascript para principiantes", em);
		assertTrue("Trabajo no encontrado", workData != null);
	}

	/**
	 * Dado un evaluador, retornar todos sus trabajos asignados.
	 */
	@Test
	public void getWorksByEvaluador() {
		List<Work> works = CacicService.getWorksByEvaluador(31156181, em);
		assertTrue("El usuario no tiene Trabajos", !works.isEmpty() && works.get(0).getName() == "Como armar una Red");
	}

	/**
	 * Dado un Revisor/Evaluador y un rango de fechas, retornar todas sus revisiones(Work con reviewed != null)
	 */
	@Test
	public void getWorksByEvaluadorRangeDate() {

		// Obtengo un usuario y un trabajo
		User userTest = UserDAO.getInstance().findByDni(31156181, em);
		Work work = WorkDAO.getInstance().findByName("Por que Micro Servicios?", em);

		// Le agrego un Trabajo al Evaluador/Usuario para revisar
		userTest.addWorkRev(work);

		// Seteo el Trabajo como revisado el 23 de Junio del 2018
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DAY_OF_MONTH, 23);
		Date reviewed = cal.getTime();
		userTest.setWorkAsReviewed(work, reviewed);

		WorkDAO.getInstance().update(work.getId(), work, em);
		UserDAO.getInstance().update(userTest.getId(), userTest, em);	

		cal.set(Calendar.YEAR, 2010);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 01);
		Date start = cal.getTime();
		cal.set(Calendar.YEAR, 2018);
		cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 30);
		Date end = cal.getTime();

		List<Work> works = CacicService.getWorksByEvaluadorRangeDate(userTest.getDni(), start, end, em);
		assertTrue("El usuario no tiene Trabajos en ese rango de fechas", !works.isEmpty() && works.get(0).getName() == "Por que Micro Servicios?");
	}

	/**
	 * Dado un autor, retornar todos sus trabajos.
	 */
	@Test
	public void getWorksByAuthor() {
		List<Work> works = CacicService.getWorksByAuthor(31156181, em);
		assertTrue("El usuario/autor no tiene Trabajos", !works.isEmpty());
	}

	/**
	 * Dado un usuario, retornar todos sus trabajos filtrados por una palabra clave.
	 */
	@Test
	public void getWorksByUserAndCategory() {

		List<Work> works = CacicService.getWorksByUserAndCategory(31156181, "Javascript", em);

		assertTrue("El usuario/autor no tiene Trabajos", !works.isEmpty() && works.get(0).getName() == "Javascript para principiantes");
	}

}
