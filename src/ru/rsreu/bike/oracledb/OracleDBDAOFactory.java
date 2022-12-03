package ru.rsreu.bike.oracledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import ru.rsreu.bike.DAO.factory.DAOFactory;
import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.intefaceDAO.ClientTypeDAO;
import ru.rsreu.bike.intefaceDAO.TripDAO;

public class OracleDBDAOFactory extends DAOFactory {
	private static volatile OracleDBDAOFactory instance;
	private Connection connection;

	private OracleDBDAOFactory() {
	}

	public static OracleDBDAOFactory getInstance() throws ClassNotFoundException, SQLException {
		OracleDBDAOFactory factory = instance;
		if (instance == null) {
			synchronized (OracleDBDAOFactory.class) {
				instance = factory = new OracleDBDAOFactory();
				factory.connected();
			}
		}
		return factory;
	}

	private void connected() throws ClassNotFoundException, SQLException {
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "oracle");
		System.out.println("Connected to oracle DB!");
	}

	public ClientDAO getClientDAO() {
		return new OracleClientDAO(connection);
	}

	public ClientTypeDAO getClientTypeDAO() {
		return new OracleClientTypeDAO(connection);
	}

	public TripDAO getTripDAO() {
		return new OracleTripDAO(connection);
	}

	public BikeDAO getBikeDAO() {
		return new OracleBikeDAO(connection);
	}

	public AddressDAO getAddressDAO() {
		return new OracleAddressDAO(connection);
	}
}
