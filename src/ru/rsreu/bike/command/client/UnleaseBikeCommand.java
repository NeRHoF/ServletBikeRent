package ru.rsreu.bike.command.client;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.intefaceDAO.TripDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class UnleaseBikeCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
		TripDAO tripDAO = (TripDAO) request.getServletContext().getAttribute("TripDAO");
		AddressDAO addressDAO = (AddressDAO) (request.getServletContext().getAttribute("AddressDAO"));
		tripDAO.finishTrip((String) request.getParameter("id"));
		BikeDAO bikeDAO = (BikeDAO) request.getServletContext().getAttribute("BikeDAO");
		bikeDAO.book((String) request.getParameter("bikeId"), "N");
		bikeDAO.changeAddress((String) request.getParameter("bikeId"),
				addressDAO.getAddressId((String) request.getParameter("addressList")));
		return new Activity(CommandEnum.GET_BIKES.toString(), ActivityEnum.SEND_REDIRECT);
	}

}
