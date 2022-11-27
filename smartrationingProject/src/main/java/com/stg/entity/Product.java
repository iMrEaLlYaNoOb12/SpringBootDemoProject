package com.stg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product {
	@Id
	private int productId;
	private String productName;
	private float productPrice;
	private int quantity;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "ration_admin_id", referencedColumnName = "ration_admin_id")
	@JsonBackReference(value = "rationAdminProducts")
	private RationAdmin rationAdmin;
	
	

	public Product() {
		super();
	}

	public Product(int productId, String productName, float productPrice, int quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}

	
	
	public RationAdmin getRationAdmin() {
		return rationAdmin;
	}

	public void setRationAdmin(RationAdmin rationAdmin) {
		this.rationAdmin = rationAdmin;
	}

	

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
