package ru.rsreu.bike.moderator;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class GetAllBikeCommand  implements ActivityCommand {

	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
		BikeDAO bikeDAO = (BikeDAO) (request.getServletContext().getAttribute("BikeDAO"));
		request.setAttribute("bikeList", bikeDAO.getAllBikeList());
		return new Activity(ConfigurationManager.getProperty("path.page.allBikes"), ActivityEnum.FORWARD);
	}

}