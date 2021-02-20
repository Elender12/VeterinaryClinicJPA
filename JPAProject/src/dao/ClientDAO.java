package dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Client;

public class ClientDAO extends DaoRepository<Client, Integer> {

	public ClientDAO(EntityManager em) {
		super(em);
	}
 
	@Override
	public Client find(Integer id) {
		EntityManager em = this.getEntityManager();
		return em.find(Client.class, id);
	}

	@Override
	public Client findByDocumentID(String documentID) {
		EntityManager em = this.getEntityManager();
		String query = "Select c FROM Client as c WHERE c.idDocument = :documentID";
		TypedQuery<Client> qr = em.createQuery(query, Client.class);
		qr.setParameter("documentID", documentID);
		return (Client) qr.getSingleResult();
	}

	@Override
	public Client create(Client entity) {
		return super.create(entity);
	}

	@Override
	public void delete(Client entity) {
		super.delete(entity);
	}

	@Override
	public Client update(Client entity) {		
		return super.update(entity);
	}

}
