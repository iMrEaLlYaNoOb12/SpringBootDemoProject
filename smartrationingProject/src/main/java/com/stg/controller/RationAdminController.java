package com.stg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.OrderStatus;
import com.stg.entity.Product;
import com.stg.entity.RationAdmin;
import com.stg.entity.RationCardMember;
import com.stg.entity.RationCard;
import com.stg.exception.RationApplicationException;
import com.stg.service.RationAdminService;

@RestController
@RequestMapping(value = "rationAdmin")
public class RationAdminController {

	@Autowired
	private RationAdminService rationAdminService;

	@PostMapping(value = "/addRationAdmin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RationAdmin> addRationAdmin(@RequestBody RationAdmin rationAdmin)
			throws RationApplicationException {
		return new ResponseEntity<RationAdmin>(rationAdminService.addRationAdmin(rationAdmin), HttpStatus.CREATED);

	}

//	@GetMapping(value = "/readAllRationAdmin")
//	public ResponseEntity<List<RationAdmin>> readAllRationAdmin() throws RationApplicationException {
//
//		return new ResponseEntity<List<RationAdmin>>(rationAdminService.readAllRationAdmin(), HttpStatus.OK);
//	}

	// @GetMapping(value = "/readRationAdmin/{rationAdminId}")
	public ResponseEntity<RationAdmin> readRationAdmin(@PathVariable int rationAdminId)
			throws RationApplicationException {
		return new ResponseEntity<RationAdmin>(rationAdminService.readRationAdmin(rationAdminId), HttpStatus.OK);
	}

//	@PutMapping(value = "/updateRationAdminPassword/")
//	public ResponseEntity<String> updatePassword(@RequestParam int rationAdminId, @RequestParam String password)
//			throws RationApplicationException {
//
//		return new ResponseEntity<String>(rationAdminService.updatePassword(rationAdminId, password),
//				HttpStatus.ACCEPTED);
//	}

	// @DeleteMapping(value = "/deleteRationAdmin/{rationAdminId}")
	public ResponseEntity<String> deleteRationAdmin(@PathVariable int rationAdminId) throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.deleteRationAdmin(rationAdminId), HttpStatus.OK);

	}

	// Order //
	@GetMapping(value = "/readAllOrders")
	public ResponseEntity<List<OrderDetails>> readOrders(@RequestParam int rationAdminId)
			throws RationApplicationException {
		return new ResponseEntity<List<OrderDetails>>(rationAdminService.readOrders(rationAdminId), HttpStatus.OK);

	}

	// RationCard User
//	@GetMapping(value = "/readRationCardUsers")
//	public ResponseEntity<List<RationCard>> readUsers(@RequestParam int rationAdminId)
//			throws RationApplicationException {
//		return new ResponseEntity<List<RationCard>>(rationAdminService.readUsers(rationAdminId), HttpStatus.OK);
//	}

	// Products
	@GetMapping(value = "/readAllProducts")
	public ResponseEntity<List<Product>> readProduct(@RequestParam int rationAdminId)
			throws RationApplicationException {
		return new ResponseEntity<List<Product>>(rationAdminService.readProduct(rationAdminId), HttpStatus.OK);

	}

	@PutMapping(value = "/updateProduct/")
	public ResponseEntity<Product> updateProduct(@RequestParam int rationAdminId, @RequestParam int productId,
			@RequestParam int quantity) throws RationApplicationException {
		return new ResponseEntity<Product>(rationAdminService.updateProduct(rationAdminId, productId, quantity),
				HttpStatus.CREATED);

	}

	// RationCardMember
	@DeleteMapping(value = "/deleteRationCardMember")
	public ResponseEntity<String> deleteRationCardMember(@RequestParam int rationAdminId,
			@RequestParam long rationCardNo, @RequestParam int memberId) throws RationApplicationException {
		return new ResponseEntity<String>(
				rationAdminService.deleteRationCardMember(rationAdminId, rationCardNo, memberId), HttpStatus.OK);

	}

	@PostMapping(value = "/addRationCardMember")
	public ResponseEntity<RationCardMember> addRationCardMember(@RequestBody RationCardMember rationCardMember,
			@RequestParam long rationCardNo) throws RationApplicationException {
		return new ResponseEntity<RationCardMember>(
				rationAdminService.addRationCardMember(rationCardMember, rationCardNo), HttpStatus.CREATED);

	}

	// RationCard
	@GetMapping(value = "/readAllRationCard")
	public ResponseEntity<List<RationCard>> readRationCard(@RequestParam int rationAdminId)
			throws RationApplicationException {
		return new ResponseEntity<>(rationAdminService.readRationCard(rationAdminId), HttpStatus.OK);

	}

	// @DeleteMapping(value = "/deleteRationCard")
	public ResponseEntity<String> deleteRationCard(@RequestParam int rationAdminId, @RequestParam long rationCardNo)
			throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.deleteRationCard(rationAdminId, rationCardNo),
				HttpStatus.OK);
	}

	@PostMapping(value = "/addRationCard")
	public ResponseEntity<RationCard> addRationCard(@RequestBody RationCard rationCard, @RequestParam int rationAdminId)
			throws RationApplicationException {
		//RationCard rationCard2=rationAdminService.addRationCard(rationCard, rationAdminId);
		
		return new ResponseEntity<RationCard>(rationAdminService.addRationCard(rationCard, rationAdminId),
				HttpStatus.CREATED);

	}

	@PutMapping(value = "/UpdateOrder/{orderStatus}")
	public ResponseEntity<String> UpdateOrderStatus(@RequestParam long rationCardNo,
			@PathVariable OrderStatus orderStatus) throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.UpdateOrderStatus(rationCardNo, orderStatus),
				HttpStatus.ACCEPTED);
	}

	// @GetMapping(value = "/searchingArea")
	public ResponseEntity<List<Address>> searchingArea(@RequestParam int rationAdminId, @RequestParam String area)
			throws RationApplicationException {

		return new ResponseEntity<List<Address>>(rationAdminService.searchingArea(rationAdminId, area), HttpStatus.OK);

	}

	// @PutMapping(value = "/SetAdminId")
	public ResponseEntity<String> UpdateAdminIdToRationCard(@RequestParam int AdminId, @RequestParam int rationId)
			throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.UpdateAdminIdToRationCard(AdminId, rationId),
				HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/addProducts")
	public ResponseEntity<Product> addProducts(@RequestBody Product products, @RequestParam int rationAdminId)
			throws RationApplicationException {
		return new ResponseEntity<Product>(rationAdminService.addProducts(rationAdminId, products), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/deleteProducts")
	public ResponseEntity<String> deleteProduct(@RequestParam int rationAdminId, @RequestParam int productId)
			throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.deleteProduct(rationAdminId, productId), HttpStatus.OK);

	}

	@PostMapping(value = "/addFixedProducts")
	public ResponseEntity<FixedProducts> addFixedProducts(@RequestBody FixedProducts fixedProducts,
			@RequestParam int rationAdminId) throws RationApplicationException {
		return new ResponseEntity<FixedProducts>(rationAdminService.addFixedProducts(rationAdminId, fixedProducts),
				HttpStatus.OK);
	}

	@GetMapping(value = "/readAllFixedProducts")
	public List<FixedProducts> readFixedProduct(@RequestParam int rationAdminId) throws RationApplicationException {
		return rationAdminService.readFixedProduct(rationAdminId);
	}

	@PutMapping(value = "/updateFixedProducts")
	public ResponseEntity<FixedProducts> updateFixedProduct(@RequestParam int rationAdminId,
			@RequestParam int productId, @RequestParam int quantity) throws RationApplicationException {
		return new ResponseEntity<FixedProducts>(
				rationAdminService.updateFixedProduct(rationAdminId, productId, quantity), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "deleteFixedProducts")
	public ResponseEntity<String> deleteFixedProduct(int rationAdminId, int productId)
			throws RationApplicationException {
		return new ResponseEntity<String>(rationAdminService.deleteFixedProduct(rationAdminId, productId),
				HttpStatus.OK);

	}

//	@PostMapping(value = "/create")
//	public ResponseEntity<Address> addAddress(@RequestBody Address address, @RequestParam int rationId)
//			throws RationApplicationException {
//		return new ResponseEntity<Address>(rationAdminService.addAddress(address, rationId), HttpStatus.CREATED);
//	}

	@GetMapping(value = "/rationCardUsers")
	public ResponseEntity<RationCard> readRationCard(long rationCardNo) throws RationApplicationException {
		RationCard card = rationAdminService.readRationCard(rationCardNo);
		card.setPassword("****");
		//card.setFixedProduct(null);
		card.setOrders(null);
		return new ResponseEntity<RationCard>(card, HttpStatus.OK);

	}

//=================================================================================
	@GetMapping(value = "AllOrdersByMonth")
	public ResponseEntity<List<OrderDetails>> readOrderBymonth(@RequestParam int month)
			throws RationApplicationException {
		return new ResponseEntity<List<OrderDetails>>(rationAdminService.readOrderBymonth(month), HttpStatus.OK);
	}

	// =================address=======================
	@PostMapping(value = "addAddress")
	public ResponseEntity<Address> addAddress(@RequestBody Address address, @RequestParam int rationId)
			throws RationApplicationException {
		return new ResponseEntity<Address>(rationAdminService.addAddress(address, rationId), HttpStatus.CREATED);
	}
	
	//=====================================Add List Of rationCardMembers========================
	@PostMapping(value = "ListOfMembers")
	public List<RationCardMember> addListRationCardMember(@RequestBody List<RationCardMember> rationCardMember,@RequestParam long rationCardNo)
			throws RationApplicationException {
		
		return rationAdminService.addListRationCardMember(rationCardMember, rationCardNo);
	}
}
