package ru.rsreu.bike.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.bike.essence.Address;
import ru.rsreu.bike.essence.Bike;
import ru.rsreu.bike.essence.Client;
import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.resourse.QueryManager;

public class OracleAddressDAO implements AddressDAO {
	private Connection connection;

	public OracleAddressDAO(Connection connection) {
		this.setConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public void createAddress(String name) {
		String query = QueryManager.getProperty("path.query.address.createAddress");
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);

			ps.setInt(1, generateId() + 1);
			ps.setString(2, name);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void deleteAddress(String id) {
		PreparedStatement st = null;

		String query = QueryManager.getProperty("path.query.address.deleteAddress");

		try {
			st = connection.prepareStatement(query);
			st.setString(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}

	}

	public int getAddressId(String name) {
		PreparedStatement st = null;
		ResultSet rs = null;
		int id = 0;

		try {
			String query = QueryManager.getProperty("path.query.address.getAddressId");
			st = connection.prepareStatement(query);
			st.setString(1, name);
			rs = st.executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
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
		return id;
	}

	public List<Address> getAddressList() {
		List<Address> addressList = new ArrayList<Address>();
		Statement st = null;
		ResultSet rs = null;
		String query = QueryManager.getProperty("path.query.address.getAddressList");
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Address address = new Address();
				address.setId(rs.getInt("id"));
				address.setAddress(rs.getString("adress"));
				addressList.add(address);
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
		return addressList;
	}

	private int generateId() {
		String query = QueryManager.getProperty("path.query.bike.generateId");
		int max = 0;
		ResultSet rs = null;
		Statement st = null;
		try {
			st = getConnection().createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				max = rs.getInt(1);
			}
			return max;

		} catch (Exception ex) {
			System.out.println("Error " + ex.getMessage());
		}
		return max;
	}

	private void fillBike(Bike bike, ResultSet resultSet) throws SQLException {
		bike.setId(resultSet.getInt("id"));

	}
}
