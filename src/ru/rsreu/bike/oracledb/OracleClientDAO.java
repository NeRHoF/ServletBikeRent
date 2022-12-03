package ru.rsreu.bike.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.bike.essence.Client;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.resourse.QueryManager;

public class OracleClientDAO implements ClientDAO {
	private Connection connection;

	public OracleClientDAO(Connection connection) {
		this.setConnection(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void updateClient(String id, String name, String login, String password) {
		String query = QueryManager.getProperty("path.query.clientDAO.updateClient");
		PreparedStatement ps = null;

		try {
			ps = connection.prepareStatement(query);

			ps.setString(1, name);
			ps.setString(2, login);
			ps.setString(3, password);
			ps.setString(4, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}

	}

	public void createClient(String name, String login, String password, int typeId) {
		String query = QueryManager.getProperty("path.query.clientDAO.createClient");
		PreparedStatement st = null;
		int clientId = generateId() + 1;
		try {
			st = connection.prepareStatement(query);
			st.setInt(1, clientId);
			st.setInt(2, typeId);
			st.setString(3, name);
			st.setString(4, login);
			st.setString(5, password);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}

	}

	public Client getClient(String login, String password) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Client client = null;
		String query = QueryManager.getProperty("path.query.clientDAO.getClient");

		try {
			st = connection.prepareStatement(query);
			st.setString(1, login);
			st.setString(2, password);
			rs = st.executeQuery();

			if (rs.next()) {
				client = new Client();
				fillClient(client, rs);
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
		return client;
	}

	public List<Client> getClientList() {
		List<Client> clientList = new ArrayList<Client>();
		Statement st = null;
		ResultSet rs = null;
		String query = QueryManager.getProperty("path.query.clientDAO.getClientList");
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Client client = new Client();
				fillClient(client, rs);
				clientList.add(client);
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

	public void setStatus(String userId, String flag) {
		String query = QueryManager.getProperty("path.query.clientDAO.setStatus");
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, flag);
			ps.setString(2, userId);
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

	public void setBlockFlag(String userId, String flag) {
		String query = QueryManager.getProperty("path.query.clientDAO.setBlockFlag");
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, flag);
			ps.setString(2, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				} else {
					System.out.println("Error close");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();

			}
		}
	}

	public void deleteClient(String id) {
		PreparedStatement st = null;

		String query = QueryManager.getProperty("path.query.clientDAO.deleteClient");

		try {
			st = connection.prepareStatement(query);
			st.setString(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}

	}

	private int generateId() {
		String query = QueryManager.getProperty("path.query.clientDAO.generateId");
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

	private void fillClient(Client client, ResultSet resultSet) throws SQLException {
		client.setId(resultSet.getString("id"));
		client.setLogin(resultSet.getString("login"));
		client.setType(resultSet.getString("type_name"));
		client.setName(resultSet.getString("name"));
		client.setStatus(resultSet.getString("status_flag").equals("Y") ? "ONLINE" : "OFFLINE");
		client.setBlock(resultSet.getString("block_flag").equals("Y") ? "BLOCKED" : "UNBLOCKED");
	}
}
