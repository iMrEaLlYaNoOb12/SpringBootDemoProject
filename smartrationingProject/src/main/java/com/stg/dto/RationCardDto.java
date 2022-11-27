package com.stg.dto;

import com.stg.entity.CardType;

public class RationCardDto {

	private int rationId;
	private long rationCardNo;
	private CardType type;

	public RationCardDto() {
		super();
	}

	public RationCardDto(int rationId, long rationCardNo, CardType type) {
		super();
		this.rationId = rationId;
		this.rationCardNo = rationCardNo;
		this.type = type;
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

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

}
