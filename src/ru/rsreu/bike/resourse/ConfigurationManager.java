package ru.rsreu.bike.resourse;

import java.util.ResourceBundle;

public class ConfigurationManager {
	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("ru.rsreu.bike.resourse.config");

	private ConfigurationManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
