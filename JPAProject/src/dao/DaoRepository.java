package dao;

import javax.persistence.EntityManager;


abstract class DaoRepository<T, K> {
	   EntityManager em;
	 
	   public DaoRepository(EntityManager em) {
	      this.em = em;
	   }
	 
	   public EntityManager getEntityManager() {
	      return this.em;
	   }
	 
	   public abstract T find(K id);
	   
	   public abstract T findByDocumentID(String documentID);
	 
	   public T create(T entity) {
	      checkTransaction();
	      em.persist(entity);
	      em.flush();
	      em.refresh(entity);
	      return entity;
	   }
	   
	   public T update(T entity) {
	      checkTransaction();
	      return (T) em.merge(entity);
	   }
	 
	   public void delete(T entity) {
	      checkTransaction();
	      entity = em.merge(entity);
	      em.remove(entity);
	   }
	 
	   private void checkTransaction() {
	      if (!em.getTransaction().isActive())
	         throw new RuntimeException("Transacción inactiva");
	   }
	}
