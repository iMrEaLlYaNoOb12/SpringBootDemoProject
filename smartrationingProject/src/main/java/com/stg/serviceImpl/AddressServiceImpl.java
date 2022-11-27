package com.stg.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.entity.Address;
import com.stg.entity.OrderDetails;
import com.stg.entity.RationCard;
import com.stg.entity.RationCardMember;
import com.stg.exception.RationApplicationException;
import com.stg.repository.AddressRepository;
import com.stg.repository.OrderRepository;
import com.stg.repository.RationCardRepository;
import com.stg.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private RationCardRepository rationCardRepository;

	@Override
	public Address addAddress(Address address, int rationId) throws RationApplicationException {
		RationCard rationCard2 = rationCardRepository.findById(rationId).get();
		if (address != null) {
			rationCard2.setAddresses(address);
			rationCardRepository.save(rationCard2);

			return addressRepository.save(address);
		} else {
			throw new RationApplicationException("New address Can't able to create");
		}
	}

	@Override
	public Address readAddress(int addressId) throws RationApplicationException {
		if (addressRepository.existsById(addressId)) {
			return addressRepository.findById(addressId).get();
		} else {
			throw new RationApplicationException("AddressId is Not found !!");
		}
	}

	@Override
	public Address updateAddress(int addressId, String area) throws RationApplicationException {
		Address address = null;
		if (addressRepository.existsById(addressId)) {
			Optional<Address> optional = addressRepository.findById(addressId);
			optional.get().setArea(area);
			address = optional.get();
			addressRepository.save(address);
			return address;
		} else {
			throw new RationApplicationException("Unable to Update ");
		}
	}

	@Override
	public String deleteAddress(int addressId) throws RationApplicationException {
		if (addressRepository.existsById(addressId)) {
			addressRepository.deleteById(addressId);
			return "Deleted Successfully";
		} else {
			throw new RationApplicationException("Unable to Update ");
		}
	}

	@Override
	public String RationCardToAddressId(int rationId, int addressId) throws RationApplicationException {
		RationCard rationCard = rationCardRepository.findById(rationId).get();
		Address address = addressRepository.findById(addressId).get();
		rationCard.setAddresses(address);
		rationCardRepository.save(rationCard);
		if (rationCard != null) {
			return "Updated Successfully";
		} else {
			throw new RationApplicationException("Ration Id is incorrect");
		}

	}

}
