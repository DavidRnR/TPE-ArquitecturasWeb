package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMF implements ServletContextListener {
	private static EntityManagerFactory emf;

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		emf = Persistence.createEntityManagerFactory("Cacic2018");	
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		emf.close();
	}

	public static EntityManager createEntityManager() {
		if (emf == null) {
			try {
				emf = Persistence.createEntityManagerFactory("Cacic2018");	
			} catch (Exception e) {
				throw new IllegalStateException("Context is not initialized yet.");
			}

		}
		return emf.createEntityManager();
	}
}
