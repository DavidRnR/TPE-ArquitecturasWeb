package app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;

import services.CacicService;

public class MainApp {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Cacic2018");
		EntityManager em = emf.createEntityManager();
		
		CacicService.createRole("autor", em);
		CacicService.createRole("evaluador", em);
		
		CacicService.createUser("David", "Martín", "carlosdavidmartin@gmail.com", em);
		CacicService.createUser("Emilio", "Martín", "emiliomartin@gmail.com", em);
		CacicService.createUser("Andrea", "Pérez", "andreaperez@hotmail.com", em);
		CacicService.createUser("Jorge", "Gómez", "gomezjorge@yahoo.com", em);
		CacicService.createUser("Camila", "Martinez", "camilamart@gmail.com", em);
		CacicService.createUser("Carolina", "Ferrari", "ferraricaro@hotmail.com", em);
		CacicService.createUser("Pedro", "Monzon", "monpedro@yahoo.com", em);
		CacicService.createUser("Esteban", "García", "estgarcia@gmail.com", em);
		CacicService.createUser("Paola", "Mendez", "pao_mendez@gmail.com", em);
		CacicService.createUser("Victoria", "Pozo", "pozovicky@gmail.com", em);
		
		CacicService.createCategory("Redes", em);
		CacicService.createCategory("IOT", em);
		CacicService.createCategory("Micro Servicios", em);
		CacicService.createCategory("AI", em);
		
//		CacicService.createArticle("Mas allá de la gran Network", new Date());
		System.out.println("Probando ******************");
		em.close();
		emf.close();
	}
}
