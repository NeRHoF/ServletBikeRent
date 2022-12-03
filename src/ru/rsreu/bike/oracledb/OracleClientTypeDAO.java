package ru.rsreu.bike.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.bike.essence.ClientType;
import ru.rsreu.bike.intefaceDAO.ClientTypeDAO;
import ru.rsreu.bike.resourse.QueryManager;

public class OracleClientTypeDAO implements ClientTypeDAO {
	private Connection connection;

	public OracleClientTypeDAO(Connection connection) {
		this.setConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public int getTypeId(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		int clientTypeId = 0;

		try {
			String query = QueryManager.getProperty("path.query.clientType.getTypeId");
			st = connection.prepareStatement(query);
			st.setString(1, name);
			rs = st.executeQuery();
			if (rs.next()) {
				clientTypeId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				} else {
					System.out.println("Error close");
				}
				if (st != null) {
					st.close();
				} else {
					System.out.println("Error statement");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();

			}
		}
		return clientTypeId;
	}

	public List<ClientType> getTypeList() {
		List<ClientType> clientTypeList = new ArrayList<ClientType>();
		Statement st = null;
		ResultSet rs = null;

		String query = QueryManager.getProperty("path.query.clientType.getTypeList");
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				ClientType userType = new ClientType();
				userType.setId(rs.getInt("id"));
				userType.setName(rs.getString("name"));
				clientTypeList.add(userType);
			}
		} catch (SQLException ex) {
			System.out.println("Error " + ex.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				} else {
					System.out.println("Error close");
				}
				if (st != null) {
					st.close();
				} else {
					System.out.println("Error statement");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();

			}
		}
		return clientTypeList;
	}
}
