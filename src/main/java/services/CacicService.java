package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import entities.Work;
import entities.User;

public class CacicService {
	public CacicService() {}

	/**
	 * Obtener Todos los Evaluadores aptos para revisar un Articulo 
	 * @param name
	 * @param em
	 * @return
	 */
	public static List<User> findEvaluadores(String name, EntityManager em) {
		List<User> result = new ArrayList<User>();
		Work work = WorkDAO.getInstance().findByName(name, em);

		if(work != null) {
			List<User> evaluadores = UserDAO.getInstance().getEvaludoares(em);
			for (User ev : evaluadores) {
				if(ev.canReviewArticle(work)) {
					result.add(ev);
				}
			}
		}

		return result;
	}

	/**
	 * Obtener todos los Trabajos que PUEDE revisar un evaluador
	 * @param dni
	 * @param em
	 * @return
	 */
	public static List<Work> findWorksToEvaluador(long dni, EntityManager em) {
		List<Work> result = new ArrayList<Work>();
		User user = UserDAO.getInstance().findByDni(dni, em);

		if(user != null && user.isEvaluador()) {
			List<Work> works = WorkDAO.getInstance().findAllNoReviewed(em);
			for (Work work : works) {
				if(user.canReviewArticle(work)) {
					result.add(work);
				}
			}
		}

		return result;
	}

	/**
	 * Obtener los Articulos que tiene un Evaluador
	 * @param dni
	 * @param em
	 * @return
	 */
	public static List<Work> getWorksByEvaluador(long dni, EntityManager em) {
		User user = UserDAO.getInstance().findByDni(dni, em);

		if(user != null && user.isEvaluador()) {
			return user.getWorksRev();
		}

		return null;
	}

	/**
	 * Obtener los Articulos que tiene un Author
	 * @param dni
	 * @param em
	 * @return
	 */
	public static List<Work> getWorksByAuthor(long dni, EntityManager em) {
		User user = UserDAO.getInstance().findByDni(dni, em);

		if(user != null && user.isAuthor()) {
			return user.getWorks();
		}

		return null;
	}
	
	/**
	 * Obtener los Articulos por Categoria que tiene un Usuario
	 * @param dni
	 * @param em
	 * @return
	 */
	public static List<Work> getWorksByUserAndCategory(long dni, String keyWord, EntityManager em) {
		User user = UserDAO.getInstance().findByDni(dni, em);
		
		if(user != null) {
			return UserDAO.getInstance().findWorksByUserAndKeyWord(user, keyWord, em);
		}

		return null;
	}

	public static boolean isEvaluadorExpert(long dni, EntityManager em) {
		User user = UserDAO.getInstance().findByDni(dni, em);

		if(user != null && user.isEvaluador()) {
			return user.isExpert();
		}

		return false;
	}

	/**
	 * Dado un DNI, obtener los datos de un Usuario
	 * @param dni
	 * @return
	 */
	public static String getUserData (long dni, EntityManager em) {
		User user = UserDAO.getInstance().findByDni(dni, em);

		if(user != null) {
			return user.toString();
		}

		return null;
	}

	/**
	 * Dado un Revisor/Evaluador y un rango de fechas, retornar todas sus revisiones(Work con reviewed != null)
	 */
	public static List<Work> getWorksByEvaluadorRangeDate (long dni, Date start, Date end, EntityManager em) {
		User user = UserDAO.getInstance().findByDni(dni, em);
		List<Work> userWorks = user.getWorksRev();
		List<Work> result = new ArrayList<Work>();

		if(user != null) {
			for (Work work : userWorks) {
				if(work.getReviewed() != null && start.before(work.getReviewed()) && end.after(work.getReviewed())) {
					result.add(work);
				}
			}
		}

		return result;
	}

	/**
	 * Dado el nombre de un Work/Trabajo, obtener sus Datos
	 * @param dni
	 * @return
	 */
	public static String getWorkData (String name, EntityManager em) {
		Work work = WorkDAO.getInstance().findByName(name, em);

		if(work != null) { 
			return work.toString();
		}
		
		return null;
	}
}
