package services;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import dao.ClientDAO;
import dao.FactoryPersistenceManager;
import model.Client;

public class ClientService {
	public void insertClient(Client client) {
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			ClientDAO clientDao = new ClientDAO(em);
			clientDao.create(client);
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} finally {
			em.close();
		}
	}

	public void editClient(Client client) {
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			ClientDAO clientDao = new ClientDAO(em);
			Client updatedClient = clientDao.update(client);
			System.out.println(updatedClient.toString());
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}finally {
			em.close();
		}
	}

	public void deleteClient(Client client) {
		EntityManager em = FactoryPersistenceManager.getInstance().createEntityManager();
		try {
			em.getTransaction().begin();
			ClientDAO clientDao = new ClientDAO(em);
			clientDao.delete(client);
			em.getTransaction().commit();
		}catch(Exception ex) {
			ex.printStackTrace(System.out);
		}
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
