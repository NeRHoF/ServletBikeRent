package ru.rsreu.bike.intefaceDAO;

import java.util.List;

import ru.rsreu.bike.essence.Client;

public interface ClientDAO {

	Client getClient(String login, String pass);

	List<Client> getClientList();


	void deleteClient(String parameter);

	void setStatus(String string2, String string);

	void createClient(String name, String login, String password, int typeId);

	void updateClient(String changeId, String name, String login, String password);

	void setBlockFlag(String parameter, String object);

}
