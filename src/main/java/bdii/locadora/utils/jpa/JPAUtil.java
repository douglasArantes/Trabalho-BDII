package bdii.locadora.utils.jpa;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil implements Serializable {
	
	private static final long serialVersionUID = -7412603409431578181L;
	
	private EntityManagerFactory factory;

	public JPAUtil() {
		factory = Persistence.createEntityManagerFactory("LocadoraPU");
	}
	
	@Produces @RequestScoped
	public EntityManager createEntityManager(){
		return factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager manager){
		manager.close();
	}

}
