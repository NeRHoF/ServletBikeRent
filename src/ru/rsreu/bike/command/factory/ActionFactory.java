package ru.rsreu.bike.command.factory;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.bike.activity.Activity;
import ru.rsreu.bike.command.CommandEnum;

public class ActionFactory {

	
	public void process(HttpServletRequest request, HttpServletResponse response) {

		try {
			String command = request.getParameter("command");

			CommandEnum currentEnum = CommandEnum.valueOf(command.toUpperCase());
			ActivityCommand currentCommand = currentEnum.getCommand();
			Activity activity = currentCommand.execute(request);

			switch (activity.getActivityType()) {
			case FORWARD:
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(activity.getPage());
				dispatcher.forward(request, response);
				break;
			case SEND_REDIRECT:
				response.sendRedirect(request.getContextPath() + "/FrontController?command=" + activity.getPage());
				break;
			}
		} catch (ServletException | IOException e) {

			e.printStackTrace();
		}
	}
}
