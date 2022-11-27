package com.stg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Address;
import com.stg.exception.RationApplicationException;
import com.stg.service.AddressService;

//@RestController
@RequestMapping(value = "/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	
	//@PostMapping(value = "/create")
	public ResponseEntity<Address> addAddress(@RequestBody Address address, @RequestParam int rationId)
			throws RationApplicationException {
		return new ResponseEntity<Address>(addressService.addAddress(address, rationId), HttpStatus.CREATED);
	}

	//@GetMapping(value = "/read/{addressId}")
	public ResponseEntity<Address> readAddress(@PathVariable int addressId) throws RationApplicationException {
		return new ResponseEntity<Address>(addressService.readAddress(addressId), HttpStatus.OK);
	}

	//@PutMapping(value = "/update")
	public ResponseEntity<Address> updateAddress(@RequestParam int addressId, @RequestParam String area)
			throws RationApplicationException {
		return new ResponseEntity<Address>(addressService.updateAddress(addressId, area), HttpStatus.ACCEPTED);
	}

	//@DeleteMapping(value = "/delete/{addressId}")
	public ResponseEntity<String> deleteAddress(@PathVariable int addressId) throws RationApplicationException {
		return new ResponseEntity<String>(addressService.deleteAddress(addressId), HttpStatus.OK);
	}

	//@PutMapping(value = "/updateAddressIdToRationCard")
	public ResponseEntity<String> RationCardToAddressId(int rationId, int addressId) throws RationApplicationException {
		return new ResponseEntity<String>(this.addressService.RationCardToAddressId(rationId, addressId),
				HttpStatus.ACCEPTED);
	}

}
