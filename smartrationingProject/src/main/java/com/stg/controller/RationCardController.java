package com.stg.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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

import com.stg.dto.FixedProductDto;
import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.RationCard;
//import com.stg.entity.RationCard;
//import com.stg.entity.RationCardUser;
import com.stg.exception.RationApplicationException;
import com.stg.service.RationCardService;

@RestController
@RequestMapping(value = "/ration")
public class RationCardController {

	@Autowired
	private RationCardService rationCardService;
	@Autowired
	private ModelMapper mapper;

	// @PostMapping(value = "/create")
	public ResponseEntity<RationCard> createRationCard(@RequestBody RationCard rationCard,
			@RequestParam int rationAdminId) throws RationApplicationException {
		return new ResponseEntity<RationCard>(rationCardService.addRationCard(rationCard, rationAdminId),
				HttpStatus.CREATED);
	}

//	@GetMapping(value = "/read/{rationId}")
//	public ResponseEntity<RationCard> readRationCard(@PathVariable int rationId) throws RationApplicationException {
//		return new ResponseEntity<RationCard>(rationCardService.readRationCard(rationId), HttpStatus.OK);
//
//	}

//	@PutMapping(value = "/update")
//	public ResponseEntity<RationCard> updateRationCard(@RequestParam long rationCardNo, @RequestParam String password)
//			throws RationApplicationException {
//		return new ResponseEntity<RationCard>(rationCardService.updatePassword(rationCardNo, password),
//				HttpStatus.ACCEPTED);
//
//	}

//	@DeleteMapping(value = "/delete/{rationCardNo}")
//	@Transactional
//	public ResponseEntity<String> deleteRationCard(@PathVariable long rationCardNo) throws RationApplicationException {
//		return new ResponseEntity<String>(rationCardService.deleteRationCard(rationCardNo), HttpStatus.OK);
//
//	}

	@PutMapping(value = "/order/{rationNo}")
	public ResponseEntity<String> createOrder(@PathVariable long rationNo) throws RationApplicationException {
		return new ResponseEntity<String>(rationCardService.orderRation(rationNo), HttpStatus.CREATED);
	}

	// orderRequiredProduct
	@PutMapping(value = "orderRequiredProduct")
	public ResponseEntity<String> orderRequiredProduct(@RequestParam long rationCardNumber,
			@RequestParam String product1, @RequestParam String product2, @RequestParam String product3,
			@RequestParam String product4, @RequestParam String product5) throws RationApplicationException {

		return new ResponseEntity<String>(rationCardService.orderRequiredProduct(rationCardNumber, product1, product2,
				product3, product4, product5), HttpStatus.CREATED);

	}

	/// orderRequiredQuantity
//	@PutMapping(value = "orderRequiredQuantity")
//	public ResponseEntity<String> orderRequiredQuantity(@RequestParam long rationCardNumber, @RequestParam int rice,
//			@RequestParam int wheat, @RequestParam int oil, @RequestParam int sugar, @RequestParam int dhal)
//			throws RationApplicationException {
//		return new ResponseEntity<String>(
//				rationCardService.orderRequiredQuantity(rationCardNumber, rice, wheat, oil, sugar, dhal),
//				HttpStatus.CREATED);
//
//	}

//	@PutMapping(value = "gett")
//	public String purchaseeByQuantity(@RequestParam String rice) {
//		return rationCardService.purchaseeByQuantity(rice);
//
//	}

	//@PutMapping(value = "orderRequiredQuantity")
	public ResponseEntity<String> orderRequiredQuantity1(@RequestParam long rationCardNumber,
			@RequestParam String product1, @RequestParam String product2, @RequestParam String product3,
			@RequestParam String product4, @RequestParam String product5) throws RationApplicationException {
		return new ResponseEntity<String>(rationCardService.orderRequiredQuantity1(rationCardNumber, product1, product2,
				product3, product4, product5), HttpStatus.ACCEPTED);

	}

	@GetMapping(value = "orderDetails")
	public ResponseEntity<List<OrderDetails>> readOrders(long rationCardNumber) throws RationApplicationException {
		return new ResponseEntity<List<OrderDetails>>(rationCardService.readOrders(rationCardNumber), HttpStatus.OK);

	}

	@GetMapping(value = "GetFixedProducts")
	public List<FixedProductDto> readfixedProduct(long rationCardNumber) throws RationApplicationException {

		List<FixedProducts> fixedProducts = rationCardService.readfixedProduct(rationCardNumber);
		Type listType = new TypeToken<List<FixedProductDto>>() {
		}.getType();
		List<FixedProductDto> productDtos = mapper.map(fixedProducts, listType);

		return productDtos;

	}

	@GetMapping(value = "checkAvailability")
	public ResponseEntity<String> findByProductRice(@RequestParam String productName)
			throws RationApplicationException {
		return new ResponseEntity<String>(rationCardService.findByProductRice(productName), HttpStatus.OK);

	}
}
