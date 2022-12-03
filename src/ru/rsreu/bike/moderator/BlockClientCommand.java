package ru.rsreu.bike.moderator;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.activity.ActivityEnum;
import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.command.factory.ActivityCommand;
import ru.rsreu.bike.intefaceDAO.ClientDAO;
import ru.rsreu.bike.resourse.ConfigurationManager;

public class BlockClientCommand implements ActivityCommand {

	@Override
	public Activity execute(HttpServletRequest request) {
		String clientId = (String) request.getSession().getAttribute("clientId");
		if (clientId == null)
			return new Activity(ConfigurationManager.getProperty("path.page.login"), ActivityEnum.FORWARD);

		ClientDAO clientDAO = (ClientDAO) request.getServletContext().getAttribute("ClientDAO");
		String blockFlag = request.getParameter("blockFlag");

		clientDAO.setBlockFlag((String) request.getParameter("id"), blockFlag.equals("BLOCKED") ? "N" : "Y");

		return new Activity(CommandEnum.MODERATOR_PAGE.toString(), ActivityEnum.SEND_REDIRECT);
	}

}
