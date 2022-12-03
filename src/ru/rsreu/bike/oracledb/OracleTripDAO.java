package ru.rsreu.bike.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.rsreu.bike.essence.Bike;
import ru.rsreu.bike.essence.Client;
import ru.rsreu.bike.essence.Trip;
import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.intefaceDAO.TripDAO;
import ru.rsreu.bike.resourse.QueryManager;

public class OracleTripDAO implements TripDAO {
	private Connection connection;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public OracleTripDAO(Connection connection) {
		this.setConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public List<Trip> getUserTripList(String userId) {
		List<Trip> tripList = new ArrayList<Trip>();
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = QueryManager.getProperty("path.query.trip.getUserTripList");
		try {
			st = connection.prepareStatement(query);
			st.setString(1, userId);
			rs = st.executeQuery();
			while (rs.next()) {
				Trip trip = new Trip();
				fillTrip(trip, rs);
				tripList.add(trip);
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
		return tripList;
	}

	private int generateId() {
		String query = QueryManager.getProperty("path.query.trip.generateId");
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

	public void createTrip(int k, String clientId, String bikeId) {
		String query = QueryManager.getProperty("path.query.trip.createTrip");
		PreparedStatement ps = null;
		Date date = new Date();
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, generateId() + 1);

			ps.setString(5, k > 1 ? formatter.format(date).toString() : "");
			ps.setString(2, clientId);
			ps.setString(3, bikeId);
			ps.setInt(4, k);
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
	public void startTrip(String k) {
		String query = QueryManager.getProperty("path.query.trip.startTrip");
		PreparedStatement ps = null;
		Date date = new Date();
		try {
			ps = connection.prepareStatement(query);
			ps.setString(2, k);

			ps.setString(1, formatter.format(date).toString());

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
	public void finishTrip(String k) {
		String query = QueryManager.getProperty("path.query.trip.finishTrip");
		PreparedStatement ps = null;
		Date date = new Date();
		try {
			ps = connection.prepareStatement(query);
			ps.setString(2, k);

			ps.setString(1, formatter.format(date).toString());

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

	private void fillTrip(Trip trip, ResultSet resultSet) throws SQLException {
		trip.setClientName(resultSet.getString("client_name"));
		trip.setId(resultSet.getInt("id"));
		trip.setStatus(resultSet.getString("status_bike_id"));
		trip.setStartTime(resultSet.getString("start_time"));
		trip.setFinishTime(resultSet.getString("finish_time"));
		trip.setBikeId(resultSet.getString("bike_id"));
		trip.setAddress(resultSet.getString("adress"));

	}
	public List<Trip> getTripList() {
		List<Trip> clientList = new ArrayList<Trip>();
		Statement st = null;
		ResultSet rs = null;
		String query = QueryManager.getProperty("path.query.trip.getTripList");
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Trip trip = new Trip();
				fillTrip(trip, rs);
				clientList.add(trip);
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
		return clientList;
	}


}
