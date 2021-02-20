package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryPersistenceManager {

	private static final String PERSISTENCE_UNIT_NAME = "JPAProject";
	protected static FactoryPersistenceManager fpm = null;
	private EntityManagerFactory emf = null;

	private FactoryPersistenceManager() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			this.setEntityManagerFactory(emf);
		}
	}

	public static FactoryPersistenceManager getInstance() {
		if (fpm == null) {
			fpm = new FactoryPersistenceManager();
		}
		return fpm;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return this.emf;
	}

	public EntityManager createEntityManager() {
		return emf.createEntityManager();
	}

}
