package ru.rsreu.bike.command.client;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.intefaceDAO.TripDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class BookBikeCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
		TripDAO tripDAO = (TripDAO) request.getServletContext().getAttribute("TripDAO");
		tripDAO.createTrip(1, clientId, request.getParameter("id"));
		BikeDAO bikeDAO = (BikeDAO) request.getServletContext().getAttribute("BikeDAO");
		bikeDAO.book((String) request.getParameter("id"),"Y");
		return new Activity(CommandEnum.SELECT_BIKE.toString() + "&id=" + (String) request.getSession().getAttribute("addressId"),
				ActivityEnum.SEND_REDIRECT);
	}

}
