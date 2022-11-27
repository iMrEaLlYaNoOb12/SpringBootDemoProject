package com.stg.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stg.entity.Product;
import com.stg.exception.RationApplicationException;
import com.stg.repository.ProductRepository;

import com.stg.service.ProductService;

@Service
public class ProductImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public String findByProductRice(String rice) throws RationApplicationException {
		Product product = productRepository.findByProductName(rice);
		if (product.getQuantity() >= 5) {
			return rice + " is Available";
		} else {
			throw new RationApplicationException(rice + " is Not Available");
		}
	}

	@Override
	public String findByProductWheat(String productName) throws RationApplicationException {
		Product product = productRepository.findByProductName(productName);
		if (product.getQuantity() >= 3) {
			return productName + " is Available";
		} else {
			throw new RationApplicationException(productName + " is Not Available");
		}
	}

	@Override
	public String findByProductOil(String oil) throws RationApplicationException {
		Product product = productRepository.findByProductName(oil);
		if (product.getQuantity() >= 1) {
			return oil + " is Available";
		} else {
			throw new RationApplicationException(oil + " is Not Available");
		}
	}

	@Override
	public String findByProductSugar(String sugar) throws RationApplicationException {
		Product product = productRepository.findByProductName(sugar);
		if (product.getQuantity() >= 1) {
			return sugar + " is Available";
		} else {
			throw new RationApplicationException(sugar + " is Not Available");
		}
	}

	@Override
	public String findByProductDhal(String dhal) throws RationApplicationException {
		Product product = productRepository.findByProductName(dhal);
		if (product.getQuantity() >= 1) {
			return dhal + " is Available";
		} else {
			throw new RationApplicationException(dhal + " is Not Available");
		}
	}

	@Override
	public Product addProduct(Product product) throws RationApplicationException {

		return productRepository.save(product);
	}

	@Override
	public String deleteProduct(int productId) throws RationApplicationException {
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			return "deleted Successfully";
		} else {
			return "Product Id is not found";
		}

	}

}
