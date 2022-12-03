package ru.rsreu.bike.DAO.factory;

import java.sql.SQLException;

import ru.rsreu.bike.oracledb.OracleDBDAOFactory;



public enum DBType {
	ORACLE {
		@Override
		public DAOFactory getDAOFactory() {
			DAOFactory oracleDBDAOFactory = null;
			try {
				oracleDBDAOFactory = OracleDBDAOFactory.getInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return oracleDBDAOFactory;
		}
	};



	public abstract DAOFactory getDAOFactory();

}
