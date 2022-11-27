package com.stg.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class RationAdmin {

	@Id
	@Column(name = "ration_admin_id")
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rationAdminId;
	private String rationAdminName;
	private String password;

	@JsonManagedReference(value = "rationAdminProducts")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rationAdmin")
	private List<Product> products;

	@JsonManagedReference(value = "rationAdminOrders")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rationAdmin")
	private List<OrderDetails> orders;

	@JsonManagedReference(value = "rationAdminrationCards")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rationAdmin")
	private List<RationCard> rationCards;

	@JsonManagedReference(value = "rationAdminFxed")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rationAdmin")
	private List<FixedProducts> fixedProducts;

	public List<OrderDetails> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}

	public List<RationCard> getRationCards() {
		return rationCards;
	}

	public void setRationCards(List<RationCard> rationCards) {
		this.rationCards = rationCards;
	}

	public RationAdmin(int rationAdminId, String rationAdminName, String password, List<Product> products,
			List<OrderDetails> orders, List<RationCard> rationCards) {
		super();
		this.rationAdminId = rationAdminId;
		this.rationAdminName = rationAdminName;
		this.password = password;
		this.products = products;
		this.orders = orders;
		this.rationCards = rationCards;
	}

	public RationAdmin(int rationAdminId, String rationAdminName, String password, List<Product> products,
			List<OrderDetails> orders, List<RationCard> rationCards, List<FixedProducts> fixedProducts) {
		super();
		this.rationAdminId = rationAdminId;
		this.rationAdminName = rationAdminName;
		this.password = password;
		this.products = products;
		this.orders = orders;
		this.rationCards = rationCards;
		this.fixedProducts = fixedProducts;
	}

//	@JsonManagedReference
//	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "rationAdmin")
//	private List<Household> households;

	public RationAdmin() {
		super();
	}

	public RationAdmin(int rationAdminId, String rationAdminName, String password, List<Product> products) {
		super();
		this.rationAdminId = rationAdminId;
		this.rationAdminName = rationAdminName;
		this.password = password;
		this.products = products;
	}

	public int getRationAdminId() {
		return rationAdminId;
	}

	public void setRationAdminId(int rationAdminId) {
		this.rationAdminId = rationAdminId;
	}

	public String getRationAdminName() {
		return rationAdminName;
	}

	public void setRationAdminName(String rationAdminName) {
		this.rationAdminName = rationAdminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<FixedProducts> getFixedProducts() {
		return fixedProducts;
	}

	public void setFixedProducts(List<FixedProducts> fixedProducts) {
		this.fixedProducts = fixedProducts;
	}

}
