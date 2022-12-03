package ru.rsreu.bike.intefaceDAO;

import java.util.List;

import ru.rsreu.bike.essence.Trip;

public interface TripDAO {

	List<Trip> getUserTripList(String clientId);

	void createTrip(int i, String clientId, String string);

	void finishTrip(String parameter);

	void startTrip(String parameter);

	List<Trip>  getTripList();

}
