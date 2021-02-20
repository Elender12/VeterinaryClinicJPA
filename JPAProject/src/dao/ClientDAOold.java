package dao;

import java.util.List;

import model.Client;

public interface ClientDAOold {

	List<Client> getClients();

	void save(Client client);

	void update(Client client);

	Client findById(String documentID);

	void deleteById(String documentID);

	void delete(Client client);
}
