package ru.rsreu.bike.command.client;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.intefaceDAO.TripDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class GetUserBikesCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
		AddressDAO addressDAO = (AddressDAO) (request.getServletContext().getAttribute("AddressDAO"));

		request.setAttribute("addressList", addressDAO.getAddressList());
		TripDAO tripDAO = (TripDAO) request.getServletContext().getAttribute("TripDAO");
		request.setAttribute("tripList", tripDAO.getUserTripList(clientId));
		return new Activity(ConfigurationManager.getProperty("path.page.bikes"), ActivityEnum.FORWARD);
	}

}
