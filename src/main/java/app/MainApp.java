package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

import services.CacicService;

public class MainApp {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cacic2018");
		EntityManager em = emf.createEntityManager();

		CacicService.createCategory("Ciencia", em);
		
		em.close();
		emf.close();
	}
}
