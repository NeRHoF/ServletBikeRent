package ru.rsreu.bike.DAO.factory;

import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.intefaceDAO.ClientTypeDAO;
import ru.rsreu.bike.intefaceDAO.TripDAO;

public abstract class DAOFactory {
	public static DAOFactory getInstance(DBType dbType) {
		return dbType.getDAOFactory();
	}

	public abstract ClientDAO getClientDAO();

	public abstract ClientTypeDAO getClientTypeDAO();

	public abstract TripDAO getTripDAO();

	public abstract BikeDAO getBikeDAO();

	public abstract AddressDAO getAddressDAO();


}
