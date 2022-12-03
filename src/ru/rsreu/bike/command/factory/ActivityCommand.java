package ru.rsreu.bike.command.factory;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.bike.activity.Activity;

public interface ActivityCommand {
	Activity execute(HttpServletRequest request);
}
