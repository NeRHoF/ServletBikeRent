package ru.rsreu.bike.intefaceDAO;

import java.util.List;

import ru.rsreu.bike.essence.Address;

public interface AddressDAO {

	List<Address> getAddressList();

	int getAddressId(String parameter);

	void deleteAddress(String parameter);

	void createAddress(String parameter);

}
