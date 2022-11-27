package com.stg.service;

import java.util.List;

import com.stg.entity.Address;
import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.OrderStatus;
import com.stg.entity.Product;
import com.stg.entity.RationAdmin;
import com.stg.entity.RationCard;
import com.stg.entity.RationCardMember;
//import com.stg.entity.RationCardUser;
//import com.stg.entity.RationCard;
import com.stg.exception.RationApplicationException;

public interface RationAdminService {

	public abstract RationAdmin addRationAdmin(RationAdmin rationAdmin) throws RationApplicationException;

//	public abstract List<RationAdmin> readAllRationAdmin() throws RationApplicationException;

	public abstract RationAdmin readRationAdmin(int rationAdminId) throws RationApplicationException;

	// public abstract String updatePassword(int rationAdminId, String password)
	// throws RationApplicationException;

	public abstract String deleteRationAdmin(int rationAdminId) throws RationApplicationException;

	public abstract List<OrderDetails> readOrders(int rationAdminId) throws RationApplicationException;

	public abstract List<OrderDetails> readOrderBymonth(int month) throws RationApplicationException;

	// PRODUCTS
	public abstract Product addProducts(int rationAdminId, Product products) throws RationApplicationException;;

	public abstract List<Product> readProduct(int rationAdminId) throws RationApplicationException;

	public abstract Product updateProduct(int rationAdminId, int productId, int quantity)
			throws RationApplicationException;

	public abstract String deleteProduct(int rationAdminId, int productId) throws RationApplicationException;

	// Fixed product
	public abstract FixedProducts addFixedProducts(int rationAdminId, FixedProducts fixedProducts)
			throws RationApplicationException;

	public abstract List<FixedProducts> readFixedProduct(int rationAdminId) throws RationApplicationException;

	public abstract FixedProducts updateFixedProduct(int rationAdminId, int productId, int quantity)
			throws RationApplicationException;

	public abstract String deleteFixedProduct(int rationAdminId, int productId) throws RationApplicationException;

//=======================================================================================================================
	public abstract String deleteRationCardMember(int rationAdminId, long rationCardNo, int memberId)
			throws RationApplicationException;

	public abstract RationCardMember addRationCardMember(RationCardMember rationCardMember, long rationCardNo)
			throws RationApplicationException;
	
	public abstract List<RationCardMember> addListRationCardMember(List<RationCardMember> rationCardMember, long rationCardNo)
			throws RationApplicationException;

	public abstract List<RationCard> readUsers(int rationAdminId) throws RationApplicationException;

	public abstract RationCard addRationCard(RationCard rationCard, int rationAdminId)
			throws RationApplicationException;

	public abstract List<RationCard> readRationCard(int rationAdminId) throws RationApplicationException;

	public abstract String deleteRationCard(int rationAdminId, long rationCardNo) throws RationApplicationException;

//=========================================================================================Address
	public abstract Address addAddress(Address address, int rationId) throws RationApplicationException;

	public abstract List<Address> searchingArea(int rationAdminId, String area) throws RationApplicationException;

	public abstract String UpdateOrderStatus(long rationCardNo, OrderStatus orderStatus)
			throws RationApplicationException;

	public abstract String UpdateAdminIdToRationCard(int AdminId, int rationId) throws RationApplicationException;

	// Read RationCard
	public abstract RationCard readRationCard(long rationCardNo) throws RationApplicationException;
	// RationApplicationException;
//===========================================================Address================================
	//public Address addAddress(Address address, int rationId) throws RationApplicationException;
}
