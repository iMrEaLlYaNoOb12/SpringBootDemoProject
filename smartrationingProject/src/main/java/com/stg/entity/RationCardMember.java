package com.stg.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class RationCardMember {

	@Id
 	@GeneratedValue(strategy = GenerationType.AUTO)
	private int memberId;
	@Column(length = 40)
	private String memberName;
	@Column(length = 3)
	private int age;
	//@Enumerated(EnumType.STRING)
	private Gender gender;
	private String relation;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "rationId", referencedColumnName = "rationId")
	@JsonBackReference(value = "rationCardMember")
	private RationCard rationCard;

	public RationCardMember() {
		super();
	}
	

	public RationCardMember(int memberId, String memberName, int age, Gender gender, String relation) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.age = age;
		this.gender = gender;
		this.relation = relation;
	}


	public RationCardMember(int memberId, String memberName, int age, Gender gender, String relation,
			RationCard rationCard) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.age = age;
		this.gender = gender;
		this.relation = relation;
		this.rationCard = rationCard;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getType() {
		return gender;
	}

	public void setType(Gender type) {
		this.gender = type;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public RationCard getRationCard() {
		return rationCard;
	}

	public void setRationCard(RationCard rationCard) {
		this.rationCard = rationCard;
	}

}
