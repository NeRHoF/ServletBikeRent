package ru.rsreu.bike.moderator;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.AddressDAO;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.intefaceDAO.ClientTypeDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class CreateAddressCommand implements ActivityCommand {

	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);

		AddressDAO addressDAO = (AddressDAO) (request.getServletContext().getAttribute("AddressDAO"));

		addressDAO.createAddress((String) request.getParameter("name"));
		return new Activity(CommandEnum.GET_ADDRESS.toString(), ActivityEnum.SEND_REDIRECT);
	}

}