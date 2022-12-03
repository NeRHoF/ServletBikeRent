package ru.rsreu.bike.command.client;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.intefaceDAO.TripDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class DriveBikeCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
		TripDAO tripDAO = (TripDAO) request.getServletContext().getAttribute("TripDAO");
		tripDAO.startTrip(request.getParameter("id"));

		return new Activity(CommandEnum.GET_BIKES.toString(), ActivityEnum.SEND_REDIRECT);
	}

}
