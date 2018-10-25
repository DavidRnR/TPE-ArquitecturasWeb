package services;

import java.util.ArrayList;
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
	 * Obtener todos los Articulos que PUEDE revisar un evaluador
	 * @param dni
	 * @param em
	 * @return
	 */
	public static List<Work> findArticlesToEvaluador(long dni, EntityManager em) {
		List<Work> result = new ArrayList<Work>();
		User user = UserDAO.getInstance().findByDni(dni, em);

		if(user != null && user.isEvaluador()) {
			List<Work> works = WorkDAO.getInstance().findAll(em);
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
	public static List<Work> getArticlesByEvaluador(long dni, EntityManager em) {
		User user = UserDAO.getInstance().findByDni(dni, em);
		
		if(user != null && user.isEvaluador()) {
			return user.getWorksRev();
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
}
