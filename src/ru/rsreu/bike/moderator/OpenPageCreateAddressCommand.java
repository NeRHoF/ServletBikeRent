package ru.rsreu.bike.moderator;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.intefaceDAO.ClientTypeDAO;
import ru.rsreu.bike.intefaceDAO.TripDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class OpenPageCreateAddressCommand implements ActivityCommand {

	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
		TripDAO tripDAO = (TripDAO) (request.getServletContext().getAttribute("TripDAO"));
		request.setAttribute("tripList", tripDAO.getTripList());
		return new Activity(ConfigurationManager.getProperty("path.page.createAddress"), ActivityEnum.FORWARD);
	}

}
