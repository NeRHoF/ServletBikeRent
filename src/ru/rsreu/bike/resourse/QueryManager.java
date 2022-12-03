package ru.rsreu.bike.resourse;

import java.util.ResourceBundle;

public class QueryManager {
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("ru.rsreu.bike.resourse.query");

	private QueryManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);

	}
}
