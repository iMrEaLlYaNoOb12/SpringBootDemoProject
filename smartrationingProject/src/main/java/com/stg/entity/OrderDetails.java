package com.stg.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class OrderDetails {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;

	private long rationNo;
	private LocalDate localDate;
	private float price;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ration_admin_id", referencedColumnName = "ration_admin_id")
	@JsonBackReference(value = "rationAdminOrders")
	private RationAdmin rationAdmin;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rationId", referencedColumnName = "rationId")
	@JsonBackReference
	private RationCard rationCard;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable( joinColumns = { @JoinColumn(name = "orderId",referencedColumnName = "orderId")},
	inverseJoinColumns = {@JoinColumn(name = "productId",referencedColumnName = "productId")})
	//@JsonManagedReference
	private List<FixedProducts> fixedProduct;
	

	public OrderDetails() {
		super();
	}


	public OrderDetails(int orderId, long rationNo, LocalDate localDate, float price, OrderStatus orderStatus,
			RationAdmin rationAdmin, RationCard rationCard, List<FixedProducts> fixedProduct) {
		super();
		this.orderId = orderId;
		this.rationNo = rationNo;
		this.localDate = localDate;
		this.price = price;
		this.orderStatus = orderStatus;
		this.rationAdmin = rationAdmin;
		this.rationCard = rationCard;
		this.fixedProduct = fixedProduct;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public long getRationNo() {
		return rationNo;
	}


	public void setRationNo(long rationNo) {
		this.rationNo = rationNo;
	}


	public LocalDate getLocalDate() {
		return localDate;
	}


	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}


	public RationAdmin getRationAdmin() {
		return rationAdmin;
	}


	public void setRationAdmin(RationAdmin rationAdmin) {
		this.rationAdmin = rationAdmin;
	}


	public RationCard getRationCard() {
		return rationCard;
	}


	public void setRationCard(RationCard rationCard) {
		this.rationCard = rationCard;
	}


	public List<FixedProducts> getFixedProduct() {
		return fixedProduct;
	}


	public void setFixedProduct(List<FixedProducts> fixedProduct) {
		this.fixedProduct = fixedProduct;
	}

	
}
