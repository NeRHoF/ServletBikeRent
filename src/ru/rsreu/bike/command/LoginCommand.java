package ru.rsreu.bike.command;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.essence.Client;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;
import ru.rsreu.bike.resourse.MessageManager;

public class LoginCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {

		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		ClientDAO clientDAO = (ClientDAO) request.getServletContext().getAttribute("ClientDAO");
		Client client = clientDAO.getClient(login, pass);
		if (client == null) {
			request.setAttribute("loginerror", MessageManager.getProperty("message.loginerror"));
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
		}
		request.getSession().setAttribute("clientId", client.getId());
		request.getSession().setAttribute("type", client.getType());
		request.getSession().setAttribute("block", client.getBlock());
		clientDAO.setStatus(client.getId(),"Y");
		switch (client.getType()) {
		case "Admin":
			return new Activity(CommandEnum.ADMIN_PAGE.toString(), ActivityEnum.SEND_REDIRECT);
		case "User":
			return new Activity(CommandEnum.USER_PAGE.toString(), ActivityEnum.SEND_REDIRECT);
		case "Moderator":
			return new Activity(CommandEnum.MODERATOR_PAGE.toString(), ActivityEnum.SEND_REDIRECT);
		}
		request.setAttribute("loginerror", MessageManager.getProperty("message.loginerror"));
		return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
	}

}
