package ru.rsreu.bike.essence;

public class Bike {
	// individual number in database
	private int id;
	// individual number address in database
	private int addressId;
	// employment status
	private String vacant;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getVacant() {
		return vacant;
	}

	public void setVacant(String vacant) {
		this.vacant = vacant;
	}

}
