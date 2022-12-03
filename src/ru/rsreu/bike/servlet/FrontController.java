package ru.rsreu.bike.servlet;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.bike.DAO.factory.DAOFactory;
import ru.rsreu.bike.DAO.factory.DBType;
import ru.rsreu.bike.command.factory.ActionFactory;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Working Get-requests
	 * 
	 * @param request  HTTP-request
	 * @param response HTTP-response
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	/**
	 * Working Post-requests
	 * 
	 * @param request  HTTP-request
	 * @param response HTTP-response
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		processRequest(request, response);
	}

	/**
	 * Initializing Servlet
	 */
	@Override
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance(DBType.ORACLE);
		this.getServletContext().setAttribute("DAOFactory", daoFactory);
		this.getServletContext().setAttribute("ClientDAO", daoFactory.getClientDAO());
		this.getServletContext().setAttribute("TripDAO", daoFactory.getTripDAO());
		this.getServletContext().setAttribute("ClientTypeDAO", daoFactory.getClientTypeDAO());
		this.getServletContext().setAttribute("BikeDAO", daoFactory.getBikeDAO());
		this.getServletContext().setAttribute("AddressDAO", daoFactory.getAddressDAO());
	}

	/***
	 * Working reuqests
	 * 
	 * @param request
	 * @param response
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) {
		new ActionFactory().process(request, response);
	}
}
