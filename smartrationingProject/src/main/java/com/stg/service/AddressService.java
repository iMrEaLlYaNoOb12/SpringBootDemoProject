package com.stg.service;

import com.stg.entity.Address;
import com.stg.entity.RationCard;
import com.stg.exception.RationApplicationException;

public interface AddressService {

	public Address addAddress(Address address, int rationId) throws RationApplicationException;

	public Address readAddress(int addressId) throws RationApplicationException;

	public Address updateAddress(int addressId, String area) throws RationApplicationException;

	public String deleteAddress(int addressId) throws RationApplicationException;

	public abstract String RationCardToAddressId(int rationId, int addressId) throws RationApplicationException;
	
	
}
