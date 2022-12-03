package ru.rsreu.bike.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import ru.rsreu.bike.intefaceDAO.ClientDAO;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionDestroyed(HttpSessionEvent arg) {
		ClientDAO clientDAO = (ClientDAO) (arg.getSession().getServletContext().getAttribute("ClientDAO"));
		String clientId = (String) arg.getSession().getAttribute("clientId");
		if (clientId != null && clientDAO != null)
			clientDAO.setStatus(clientId, "N");
		arg.getSession().invalidate();
		System.out.println("Session Destroyed");
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {

	}
}