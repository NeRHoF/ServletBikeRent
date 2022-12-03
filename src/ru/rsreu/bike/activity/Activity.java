package ru.rsreu.bike.activity;

public class Activity {
	private String page;
	private ActivityEnum activity;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public ActivityEnum getActivityType() {
		return activity;
	}

	public void setActivity(ActivityEnum activity) {
		this.activity = activity;
	}

	public Activity(String page, ActivityEnum activity) {
		super();
		this.page = page;
		this.activity = activity;
	}

}
