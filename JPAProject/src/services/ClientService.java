package services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import dao.ClientDAO;
import dao.FactoryPersistenceManager;
import model.Client;

public class ClientService {
	
	public boolean insertClient(Client client) {
		boolean res = false;
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			ClientDAO clientDao = new ClientDAO(em);
			clientDao.create(client);
			em.getTransaction().commit();
			res = true;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			res= false;
		} finally {
			em.close();
		}
		return res;
	}

	public boolean editClient(Client client) {
		boolean res = false;
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			ClientDAO clientDao = new ClientDAO(em);
			Client updatedClient = clientDao.update(client);
			System.out.println(updatedClient.toString());
			em.getTransaction().commit();
			res = true;
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
			res= false;
		}finally {
			em.close();
		}
		return res;
	}

	public boolean deleteClient(Client client) {
		boolean res = false;
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			ClientDAO clientDao = new ClientDAO(em);
			clientDao.delete(client);
			em.getTransaction().commit();
			res = true;
		}catch(Exception ex) {
			ex.printStackTrace(System.out);
			res= false;
		}finally {
			em.close();
		}
		return res;
	}

	public Client checkClientByDocumentID(String documentID) {
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		Client client = null;
		try {
			em.getTransaction().begin();
			ClientDAO clientDao = new ClientDAO(em);
			client = clientDao.findByDocumentID(documentID);
		} catch (NoResultException ex) {
			System.out.println("No client found with ID: " + documentID);
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} finally {
			em.close();
		}
		return client;
	}
}
