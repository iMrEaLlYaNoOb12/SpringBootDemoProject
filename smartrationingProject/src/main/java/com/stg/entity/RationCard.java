package com.stg.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.val;

@Entity
public class RationCard {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rationId;
	@Column(length = 12, unique = true)
	private long rationCardNo;
	@Column(length = 8)
	private String password;
	// @Enumerated(EnumType.STRING)
	private CardType type;

	@JsonManagedReference(value = "rationCardAddress")
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "rationCard")
	private Address addresses;

	@JsonManagedReference(value = "rationCardMember")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rationCard")
	private List<RationCardMember> rationCardMembers;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ration_admin_id", referencedColumnName = "ration_admin_id")
	@JsonBackReference(value = "rationAdminrationCards")
	private RationAdmin rationAdmin;

//	@JsonManagedReference(value = "rationCardFxed")
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rationCard")
//	private List<FixedProducts> fixedProducts;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rationCard")
	private List<OrderDetails> orders;

	
	
	public RationCard() {
		super();
	}



	public RationCard(int rationId, long rationCardNo, String password, CardType type, Address addresses,
			List<RationCardMember> rationCardMembers, RationAdmin rationAdmin, List<OrderDetails> orders) {
		super();
		this.rationId = rationId;
		this.rationCardNo = rationCardNo;
		this.password = password;
		this.type = type;
		this.addresses = addresses;
		this.rationCardMembers = rationCardMembers;
		this.rationAdmin = rationAdmin;
		this.orders = orders;
	}



	public int getRationId() {
		return rationId;
	}



	public void setRationId(int rationId) {
		this.rationId = rationId;
	}



	public long getRationCardNo() {
		return rationCardNo;
	}



	public void setRationCardNo(long rationCardNo) {
		this.rationCardNo = rationCardNo;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public CardType getType() {
		return type;
	}



	public void setType(CardType type) {
		this.type = type;
	}



	public Address getAddresses() {
		return addresses;
	}



	public void setAddresses(Address addresses) {
		this.addresses = addresses;
	}



	public List<RationCardMember> getRationCardMembers() {
		return rationCardMembers;
	}



	public void setRationCardMembers(List<RationCardMember> rationCardMembers) {
		this.rationCardMembers = rationCardMembers;
	}



	public RationAdmin getRationAdmin() {
		return rationAdmin;
	}



	public void setRationAdmin(RationAdmin rationAdmin) {
		this.rationAdmin = rationAdmin;
	}



	public List<OrderDetails> getOrders() {
		return orders;
	}



	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}

	
	
//	@OneToOne
//	@JoinColumn(name = "householdId", referencedColumnName = "householdId")
//	@JsonBackReference(value = "houseHoldRation")
//	private Household household;

}
