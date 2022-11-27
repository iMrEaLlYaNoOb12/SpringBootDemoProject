package com.stg.entity;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Address {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;
	@Column(length = 50)
	private String addressLine1;
	@Column(length = 50)
	private String area;
	@Column(length = 40)
	private String city;
	@Column(length = 6)
	private int pincode;

	@OneToOne
	@JoinColumn(name = "rationId", referencedColumnName = "rationId")
	@JsonBackReference(value = "rationCardAddress")
	private RationCard rationCard;

	public Address() {
		super();
	}

	public Address(int addressId, String addressLine1, String area, String city, int pincode) {
		super();
		this.addressId = addressId;
		this.addressLine1 = addressLine1;
		this.area = area;
		this.city = city;
		this.pincode = pincode;
	}

	public RationCard getRationCard() {
		return rationCard;
	}

	public void setRationCard(RationCard rationCard) {
		this.rationCard = rationCard;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

}
