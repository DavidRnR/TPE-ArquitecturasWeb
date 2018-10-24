package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import entities.Article;
import entities.User;

public class CacicService {
	public CacicService() {}

	/**
	 * Obtener Todos los Evaluadores aptos para revisar un Articulo 
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

	/**
	 * Obtener todos los Articulos que puede revisar un evaluador
	 * @param dni
	 * @param em
	 * @return
	 */
	public static List<Article> findArticlesToEvaluador(int dni, EntityManager em) {
		List<Article> result = new ArrayList<Article>();
		User user = UserDAO.getInstance().findById(dni, em);

		if(user != null && user.isEvaluador()) {
			List<Article> articles = ArticleDAO.getInstance().findAll(em);
			for (Article article : articles) {
				if(user.canReviewArticle(article)) {
					result.add(article);
				}
			}
		}

		return result;
	}

	/**
	 * Obtener la cantidad de Articulos que tiene un Evaluador
	 * @param dni
	 * @param em
	 * @return
	 */
	public static int getQuantityArticlesByEvaluador(int dni, EntityManager em) {
		User user = UserDAO.getInstance().findById(dni, em);

		if(user != null && user.isEvaluador()) {
			return user.getArticlesRev().size();
		}

		return 0;
	}


	public static boolean isEvaluadorExpert(int dni, EntityManager em) {
		User user = UserDAO.getInstance().findById(dni, em);

		if(user != null && user.isEvaluador()) {
			return user.isExpert();
		}

		return false;
	}
}
