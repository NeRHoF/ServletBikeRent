package ru.rsreu.bike.command;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class LogoutCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		ClientDAO clientDAO = (ClientDAO) request.getServletContext().getAttribute("ClientDAO");
		String clientId = (String) request.getSession().getAttribute("clientId");
		clientDAO.setStatus(clientId, "N");
		request.getSession().invalidate();
		return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);
	}

}
