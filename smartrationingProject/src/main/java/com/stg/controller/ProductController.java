
package com.stg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stg.entity.Product;
import com.stg.exception.RationApplicationException;

import com.stg.service.ProductService;

//@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService product;

	//@GetMapping(value = "/rice")
	public ResponseEntity<String> findByProductRice(@RequestParam String rice) throws RationApplicationException {
		return new ResponseEntity<String>(product.findByProductRice(rice), HttpStatus.OK);

	}

	//@GetMapping(value = "/wheat")
	public ResponseEntity<String> findByProductWheat(@RequestParam String wheat) throws RationApplicationException {
		return new ResponseEntity<String>(product.findByProductWheat(wheat), HttpStatus.OK);

	}

	//@GetMapping(value = "/oil")
	public ResponseEntity<String> findByProductOil(@RequestParam String oil) throws RationApplicationException {
		return new ResponseEntity<String>(product.findByProductOil(oil), HttpStatus.OK);

	}

	//@GetMapping(value = "/sugar")
	public ResponseEntity<String> findByProductSugar(@RequestParam String sugar) throws RationApplicationException {
		return new ResponseEntity<String>(product.findByProductSugar(sugar), HttpStatus.OK);

	}

	//@GetMapping(value = "/dhal")
	public ResponseEntity<String> findByProductdhal(@RequestParam String dhal) throws RationApplicationException {
		return new ResponseEntity<String>(product.findByProductOil(dhal), HttpStatus.OK);

	}

	//@PostMapping(value = "/add")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws RationApplicationException {
		return new ResponseEntity<Product>(this.product.addProduct(product), HttpStatus.CREATED);

	}

	//@DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteProduct(@RequestParam int ProductId) throws RationApplicationException {
		return new ResponseEntity<String>(this.product.deleteProduct(ProductId), HttpStatus.OK);
	}

}
