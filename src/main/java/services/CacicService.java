package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import entities.Article;
import entities.User;

public class CacicService {

	public CacicService() {}
	
	/**
	 * Todos los Evaluadores aptos para revisar un Articulo 
	 * @param article
	 * @param em
	 * @return
	 */
	public static List<User> findEvaluadores(Article article, EntityManager em) {
		List<User> result = new ArrayList<User>();
		List<User> evaluadores = UserDAO.getInstance().getEvaludoares(em);
		for (User ev : evaluadores) {
			if(ev.canReviewArticle(article)) {
				result.add(ev);
			}
		}
		return result;
	}
}
