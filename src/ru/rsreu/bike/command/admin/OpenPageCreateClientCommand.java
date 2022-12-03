package ru.rsreu.bike.command.admin;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.ClientTypeDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class OpenPageCreateClientCommand implements ActivityCommand {

	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);

		ClientTypeDAO clientTypeDAO = (ClientTypeDAO) (request.getServletContext().getAttribute("ClientTypeDAO"));

		request.setAttribute("typeList", clientTypeDAO.getTypeList());
		return new Activity(ConfigurationManager.getProperty("path.page.newClient"), ActivityEnum.FORWARD);
	}

}
