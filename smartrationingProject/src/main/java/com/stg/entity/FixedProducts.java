package com.stg.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class FixedProducts {
	@Id
	private int productId;
	private String productName;
	private float productPrice;
	private int productQuantity;
	private CardType cardType;

//	@JsonBackReference(value = "rationCardFxed")
//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "rationId", referencedColumnName = "rationId")
//	private RationCard rationCard;

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ration_admin_id", referencedColumnName = "ration_admin_id")
	@JsonBackReference(value = "rationAdminFxed")
	private RationAdmin rationAdmin;
	
	
	@ManyToMany(mappedBy = "fixedProduct",fetch = FetchType.LAZY)
	@JsonBackReference
	private List<OrderDetails>orderDetails ;
	

	public FixedProducts() {
		super();
	}


	public FixedProducts(int productId, String productName, float productPrice, int productQuantity, CardType cardType,
			RationAdmin rationAdmin, List<OrderDetails> orderDetails) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.cardType = cardType;
		this.rationAdmin = rationAdmin;
		this.orderDetails = orderDetails;
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


	public int getProductQuantity() {
		return productQuantity;
	}


	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}


	public CardType getCardType() {
		return cardType;
	}


	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}


	public RationAdmin getRationAdmin() {
		return rationAdmin;
	}


	public void setRationAdmin(RationAdmin rationAdmin) {
		this.rationAdmin = rationAdmin;
	}


	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}


	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}


		
}
