package ru.rsreu.bike.command.admin;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class DeleteClientCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);

		ClientDAO clientDAO = (ClientDAO) request.getServletContext().getAttribute("ClientDAO");

		clientDAO.deleteClient((String) request.getParameter("id"));
		return new Activity(CommandEnum.ADMIN_PAGE.toString(), ActivityEnum.SEND_REDIRECT);
	}




}
