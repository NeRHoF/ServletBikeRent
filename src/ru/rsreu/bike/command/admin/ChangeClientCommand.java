package ru.rsreu.bike.command.admin;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class ChangeClientCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		String changeId = (String) request.getSession().getAttribute("changeId");

		ClientDAO client = (ClientDAO) (request.getServletContext().getAttribute("ClientDAO"));
		client.updateClient(changeId, name, login, password);
		return new Activity(CommandEnum.ADMIN_PAGE.toString(), ActivityEnum.SEND_REDIRECT);
	}

}
