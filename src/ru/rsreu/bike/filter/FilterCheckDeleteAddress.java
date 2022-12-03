package ru.rsreu.bike.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.bike.command.CommandEnum;
import ru.rsreu.bike.intefaceDAO.BikeDAO;
import ru.rsreu.bike.resourse.MessageManager;

public class FilterCheckDeleteAddress implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String command = httpRequest.getParameter("command");
		if (command != null && CommandEnum.DELETE_ADDRESS.toString().equals(command.toUpperCase())) {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			String id = httpRequest.getParameter("id");
			if (checkBike(request, id)) {
				redirect(httpResponse, httpRequest, ((HttpServletRequest) request).getSession(),
						CommandEnum.GET_ADDRESS, "errorBike", MessageManager.getProperty("message.errorBike"));
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	public void destroy() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public boolean checkBike(ServletRequest request, String id) {
		BikeDAO bikeDAO = (BikeDAO) request.getServletContext().getAttribute("BikeDAO");
		return bikeDAO.checkBike(id);
	}

	public void redirect(HttpServletResponse httpResponse, HttpServletRequest httpRequest, HttpSession session,
			CommandEnum command, String errorName, String errorText) throws IOException {
		session.setAttribute(errorName, errorText);
		httpResponse.sendRedirect(httpRequest.getContextPath() + "/FrontController?command=" + command.toString());
	}
}
