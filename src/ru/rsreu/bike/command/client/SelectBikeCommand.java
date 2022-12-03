package ru.rsreu.bike.command.client;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class SelectBikeCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
		request.getSession().setAttribute("addressId", (String) request.getParameter("id"));
		BikeDAO bikeDAO = (BikeDAO) request.getServletContext().getAttribute("BikeDAO");
		request.setAttribute("bikeList", bikeDAO.getFreeBikeList((String) request.getParameter("id")));
		return new Activity(ConfigurationManager.getProperty("path.page.selectBike"), ActivityEnum.FORWARD);
	}

}
