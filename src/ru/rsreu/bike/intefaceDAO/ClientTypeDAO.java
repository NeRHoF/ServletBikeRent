package ru.rsreu.bike.intefaceDAO;

import java.util.List;

import ru.rsreu.bike.essence.ClientType;

public interface ClientTypeDAO {

	List<ClientType> getTypeList();

	int getTypeId(String type);

}
