package com.stg.service;

import java.util.List;

import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.RationCard;
//import com.stg.entity.RationCard;
//import com.stg.entity.RationCardUser;
import com.stg.exception.RationApplicationException;

public interface RationCardService {

	public abstract RationCard addRationCard(RationCard rationCard, int RationAdminId)
			throws RationApplicationException;

	// public abstract RationCard readRationCard(int rationId) throws
	// RationApplicationException;

	// public abstract RationCard updatePassword(long rationCardNo, String password)
	// throws RationApplicationException;

	// public abstract String deleteRationCard(long rationCardNo) throws
	// RationApplicationException;

	public abstract String orderRation(long rationCardNumber) throws RationApplicationException;

	public abstract String orderRequiredProduct(long rationCardNumber, String product1, String product2, String product3,
			String product4, String product5) throws RationApplicationException;;

//	public abstract String orderRequiredQuantity(long rationCardNumber, int rice, int wheat, int oil, int sugar,
//			int dhal) throws RationApplicationException;

	// public String purchaseeByQuantity(String rice);

	public abstract String orderRequiredQuantity1(long rationCardNumber, String product1, String product2,
			String product3, String product4, String product5) throws RationApplicationException;

	public abstract List<OrderDetails> readOrders(long rationCardNumber) throws RationApplicationException;

	public abstract List<FixedProducts> readfixedProduct(long rationCardNumber) throws RationApplicationException;
	
	public abstract String findByProductRice(String productName) throws RationApplicationException;

}
