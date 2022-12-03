package ru.rsreu.bike.resourse;

import java.util.ResourceBundle;

public class MessageManager {
	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("ru.rsreu.bike.resourse.messages");

	private MessageManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}