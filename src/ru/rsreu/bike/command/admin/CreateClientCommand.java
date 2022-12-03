package ru.rsreu.bike.command.admin;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.intefaceDAO.ClientTypeDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class CreateClientCommand implements ActivityCommand {

	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String type = request.getParameter("typeList");

		ClientDAO clientDAO = (ClientDAO) (request.getServletContext().getAttribute("ClientDAO"));
		ClientTypeDAO clientType = (ClientTypeDAO) (request.getServletContext().getAttribute("ClientTypeDAO"));
		int typeId = clientType.getTypeId(type);

		clientDAO.createClient(name, login, password, typeId);
		return new Activity(CommandEnum.ADMIN_PAGE.toString(), ActivityEnum.SEND_REDIRECT);
	}

}