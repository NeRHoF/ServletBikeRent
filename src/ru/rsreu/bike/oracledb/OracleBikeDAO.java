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
import ru.rsreu.bike.essence.Trip;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.resourse.QueryManager;

public class OracleBikeDAO implements BikeDAO {
	private Connection connection;

	public OracleBikeDAO(Connection connection) {
		this.setConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void book(String bikeId, String flag) {
		String query = QueryManager.getProperty("path.query.bike.book");
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);

			ps.setString(1, flag);
			ps.setString(2, bikeId);
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

	public List<Bike> getAllBikeList() {
		List<Bike> bikeList = new ArrayList<Bike>();
		Statement st = null;
		ResultSet rs = null;
		String query = QueryManager.getProperty("path.query.bike.getAllBikeList");
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Bike bike = new Bike();
				fillBike(bike, rs);
				bikeList.add(bike);
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
		return bikeList;
	}

	public void changeAddress(String bikeId, int addressId) {
		String query = QueryManager.getProperty("path.query.bike.changeAddress");
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);

			ps.setString(2, bikeId);
			ps.setInt(1, addressId);
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

	public void createBike(int addressId) {
		String query = QueryManager.getProperty("path.query.bike.createBike");
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);

			ps.setInt(1, generateId() + 1);
			ps.setInt(2, addressId);
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

	public List<Bike> getUserBikeList(String userId) {
		List<Bike> bikeList = new ArrayList<Bike>();
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = QueryManager.getProperty("path.query.bike.getUserBikeList");
		try {
			st = connection.prepareStatement(query);
			st.setString(1, userId);
			rs = st.executeQuery();
			while (rs.next()) {
				Bike bBike = new Bike();
				fillBike(bBike, rs);
				bikeList.add(bBike);
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
		return bikeList;
	}

	public List<Bike> getFreeBikeList(String addressId) {
		List<Bike> bikeList = new ArrayList<Bike>();
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = QueryManager.getProperty("path.query.bike.getFreeBikeList");
		try {
			st = connection.prepareStatement(query);
			st.setString(1, addressId);
			rs = st.executeQuery();
			while (rs.next()) {
				Bike bBike = new Bike();
				fillBike(bBike, rs);
				bikeList.add(bBike);
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
		return bikeList;

	}

	private void fillBike(Bike bike, ResultSet resultSet) throws SQLException {
		bike.setId(resultSet.getInt("id"));
		bike.setVacant(resultSet.getString("vacant"));

	}

	public boolean checkBike(String bikeId) {
		PreparedStatement st = null;
		ResultSet rs = null;

		String query = QueryManager.getProperty("path.query.bike.checkBike");
		boolean flag = false;
		try {
			st = connection.prepareStatement(query);
			st.setString(1, bikeId);

			rs = st.executeQuery();

			if (rs.next()) {
				flag = true;
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
		return flag;
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

}
