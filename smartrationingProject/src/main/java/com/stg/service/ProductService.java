package com.stg.service;

import com.stg.entity.Product;
import com.stg.exception.RationApplicationException;

public interface ProductService {

	public abstract String findByProductRice(String productName) throws RationApplicationException;

	public abstract String findByProductWheat(String productName) throws RationApplicationException;

	public abstract String findByProductOil(String productName) throws RationApplicationException;

	public abstract String findByProductSugar(String productName) throws RationApplicationException;

	public abstract String findByProductDhal(String productName) throws RationApplicationException;

	public abstract Product addProduct(Product product) throws RationApplicationException;

	public abstract String deleteProduct(int productId) throws RationApplicationException;

}
