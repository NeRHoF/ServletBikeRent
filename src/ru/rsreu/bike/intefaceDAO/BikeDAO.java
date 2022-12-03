package ru.rsreu.bike.intefaceDAO;

import java.util.List;

import ru.rsreu.bike.essence.Bike;

public interface BikeDAO {

	List<Bike> getFreeBikeList(String s);

	void book(String parameter, String string);

	void createBike(int addressId);

	List<Bike> getAllBikeList();

	boolean checkBike(String id);

	void changeAddress(String parameter, int i);

}
