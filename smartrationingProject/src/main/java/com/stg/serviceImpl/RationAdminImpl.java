package com.stg.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.stg.entity.Address;
import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.OrderStatus;
import com.stg.entity.Product;
import com.stg.entity.RationAdmin;
import com.stg.entity.RationCard;
import com.stg.entity.RationCardMember;
//import com.stg.entity.RationCardUser;
import com.stg.exception.RationApplicationException;
import com.stg.repository.AddressRepository;
import com.stg.repository.FixedProductRepository;
import com.stg.repository.OrderRepository;
import com.stg.repository.ProductRepository;
import com.stg.repository.RationAdminRepository;
import com.stg.repository.RationCardMemberRepository;
import com.stg.repository.RationCardRepository;
import com.stg.service.RationAdminService;

@Service
public class RationAdminImpl implements RationAdminService {

	@Autowired
	private RationAdminRepository rationAdminRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private FixedProductRepository fixedProductRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private RationCardRepository rationCardRepository;
	@Autowired
	private RationCardMemberRepository rationCardMemberRepository;

	// Ration Admin/

	@Override
	public RationAdmin addRationAdmin(RationAdmin rationAdmin) throws RationApplicationException {
//		if (!(String.valueOf(rationAdmin.getRationAdminId()).length() > 0)) {
//			throw new RationApplicationException("Enter the Number");
//		}
//		if (!rationAdmin.getRationAdminName().matches("[a-zA-Z0-9]+")) {
//			throw new RationApplicationException("Enter the Name without SpecialCharacter");
//		}
//		if (!rationAdmin.getPassword().matches("[a-zA-Z0-9-@#]{6,8}")) {
//			throw new RationApplicationException("Enter the Correct pattern pasword");
//		}
		if (rationAdmin != null) {
			return rationAdminRepository.save(rationAdmin);
		} else {
			throw new RationApplicationException("RationAdmin is Null");
		}

	}

//	@Override
//	public List<RationAdmin> readAllRationAdmin() throws RationApplicationException {
//
//		List<RationAdmin> rationAdmins = rationAdminRepository.findAll();
//
//		if (rationAdmins != null) {
//			return rationAdmins;
//		} else {
//			throw new RationApplicationException("RationAdmin is Null ");
//		}
//
//	}

	@Override
	public RationAdmin readRationAdmin(int rationAdminId) throws RationApplicationException {
		if (String.valueOf(rationAdminId).length() > 0) {
			RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
			return rationAdmin;
		} else {
			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
		}
	}

//	@Override
//	public String updatePassword(int rationAdminId, String password) throws RationApplicationException {
//		RationAdmin rationAdmin = null;
//		if (rationAdminRepository.existsById(rationAdminId)) {
//			Optional<RationAdmin> optional = rationAdminRepository.findById(rationAdminId);
//			optional.get().setPassword(password);
//			rationAdmin = optional.get();
//			rationAdminRepository.save(rationAdmin);
//			return "Password Updated Successfully";
//		} else {
//			throw new RationApplicationException("Unable to Update");
//		}
//	}

	@Override
	public String deleteRationAdmin(int rationAdminId) throws RationApplicationException {
		if (rationAdminRepository.existsById(rationAdminId)) {
			rationAdminRepository.deleteById(rationAdminId);
			return "rationAdminId" + rationAdminId + " Deleted Successfully";
		} else {
			throw new RationApplicationException("Unable to delete");
		}
	}

	// RationAdmin----Order//
	@Override
	public List<OrderDetails> readOrders(int rationAdminId) throws RationApplicationException {

		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		if (rationAdminRepository.existsById(rationAdminId)) {

			return orderRepository.findAll();

		} else {
			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
		}

	}

	// RationAdmin---------RationCardUsers//
	@Override
	public List<RationCard> readUsers(int rationAdminId) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		if (rationAdminRepository.existsById(rationAdminId)) {

			return rationAdmin.getRationCards();
		}

		else {
			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
		}

	}

	// RationAdmin---------------Product//
	@Override
	public List<Product> readProduct(int rationAdminId) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		if (rationAdminRepository.existsById(rationAdminId)) {

			return rationAdmin.getProducts();

		} else {
			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
		}

	}

	@Override
	public Product updateProduct(int rationAdminId, int productId, int quantity) throws RationApplicationException {

		if (rationAdminRepository.existsById(rationAdminId)) {
			if (productRepository.existsById(productId)) {
				Product product = productRepository.findById(productId).get();
				product.setQuantity(quantity);
				return productRepository.save(product);
			} else {
				throw new RationApplicationException(productId + " Given product Id is Not Found");
			}
		} else {
			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
		}

	}

	// RationAdmin------RationCardMember//
	@Override
	public String deleteRationCardMember(int rationAdminId, long rationCardNo, int memberId)
			throws RationApplicationException {
		if (rationAdminRepository.existsById(rationAdminId)) {
			RationCard rationCardUser = rationCardRepository.findByRationCardNo(rationCardNo);
			if (rationCardUser.getRationCardNo() == rationCardNo) {
				rationCardMemberRepository.deleteById(memberId);
				return "RationCardMember Deleted successfully";
			} else {
				throw new RationApplicationException(rationCardNo + "You Have Entered Wrong RationCard No");
			}
		} else {
			throw new RationApplicationException(rationAdminId + " Given product Id is Not Found");
		}

	}

	@Override
	public RationCardMember addRationCardMember(RationCardMember rationCardMember, long rationCardNo)
			throws RationApplicationException {
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNo);
		if (rationCardMember != null) {
			rationCardMember.setRationCard(rationCard);
			return rationCardMemberRepository.save(rationCardMember);
		} else {
			throw new RationApplicationException("can't able to create");
		}
	}

	// Ration Card User
	@Override
	public List<RationCard> readRationCard(int rationAdminId) throws RationApplicationException {

		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		if (rationAdminRepository.existsById(rationAdminId)) {

			return rationAdmin.getRationCards();

		} else {
			throw new RationApplicationException(rationAdminId + "You Have Entered Wrong  rationAdminId ");
		}

	}

	@Override
	public String deleteRationCard(int rationAdminId, long rationCardNo) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		if (rationAdminRepository.existsById(rationAdminId)) {

			RationCard rationCardUser = rationCardRepository.findByRationCardNo(rationCardNo);
			if (rationCardUser.getRationCardNo() == rationCardNo) {
				rationCardRepository.delete(rationCardUser);
				return "RationCard Deleted successfully";
			} else {
				throw new RationApplicationException(rationCardNo + "You Have Entered Wrong RationCard No");
			}
		}

		else {

			throw new RationApplicationException(rationAdminId + " Given rationAdmin Id is Not Found");
		}
	}

	@Override
	public RationCard addRationCard(RationCard rationCard, int rationAdminId) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		List<FixedProducts> fixedProducts=fixedProductRepository.findAll();
		rationCard.setRationAdmin(rationAdmin);
		List<FixedProducts> fixedProducts3=new ArrayList<>();
		
//		for (FixedProducts fixedProducts2 : fixedProducts) {
//			if (rationCard.getType().equals(fixedProducts2.getCardType())) {
//
//				fixedProducts3.add(fixedProducts2);
//			}
//		}
//		rationCard.setFixedProduct(fixedProducts3);
		return rationCardRepository.save(rationCard);
//	
	}

//	
//			Address address = rationCard.getAddresses();
//			if (address.getArea().equalsIgnoreCase(area)) {

	@Override
	public List<Address> searchingArea(int rationAdminId, String area) throws RationApplicationException {

		return addressRepository.findAllByArea(area);

	}

	@Override
	public String UpdateOrderStatus(long rationCardNo, OrderStatus orderStatus) throws RationApplicationException {
		OrderDetails orderDetails = orderRepository.findByRationNo(rationCardNo);
		if (orderDetails != null) {
			orderDetails.setOrderStatus(orderStatus);
			orderRepository.save(orderDetails);
			return "Order Status Updated Successfully";
		} else {
			throw new RationApplicationException("Unable to update");
		}

	}

	@Override
	public String UpdateAdminIdToRationCard(int AdminId, int rationId) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(AdminId).get();
		RationCard rationCard = rationCardRepository.findById(rationId).get();
		rationCard.setRationAdmin(rationAdmin);
		rationCardRepository.save(rationCard);
		if (rationAdmin != null) {
			return "Updated Successfully";
		} else {
			throw new RationApplicationException("Admin Id is incorrect");
		}
	}

	@Override
	public Product addProducts(int rationAdminId, Product products) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		products.setRationAdmin(rationAdmin);
		return productRepository.save(products);
	}

	@Override
	public String deleteProduct(int rationAdminId, int productId) throws RationApplicationException {

		if (rationAdminRepository.existsById(rationAdminId)) {
			productRepository.deleteById(productId);
			return "Deleted successfully";

		} else {
			throw new RationApplicationException("Admin Id is incorrect");
		}
	}

	@Override
	public FixedProducts addFixedProducts(int rationAdminId, FixedProducts fixedProducts)
			throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		List<RationCard> rationCards = rationCardRepository.findAll();
		if (rationAdminRepository.existsById(rationAdminId)) {
			fixedProducts.setRationAdmin(rationAdmin);
//			List<RationCard> list=new ArrayList<>();
//			
//			for (RationCard rationCard : rationCards) {
//				if (rationCard.getType().equals(fixedProducts.getCardType())) {
//
//					list.add(rationCard);
//				}
//			}
//			fixedProducts.setCards(list);
			return fixedProductRepository.save(fixedProducts);
		} else {
			throw new RationApplicationException("Admin Id is incorrect");
		}
	}

	@Override
	public List<FixedProducts> readFixedProduct(int rationAdminId) throws RationApplicationException {
		if (rationAdminRepository.existsById(rationAdminId)) {
			return fixedProductRepository.findAll();
		} else {
			throw new RationApplicationException("Admin Id is incorrect");
		}

	}

	@Override
	public FixedProducts updateFixedProduct(int rationAdminId, int productId, int quantity)
			throws RationApplicationException {
		if (rationAdminRepository.existsById(rationAdminId)) {
			if (fixedProductRepository.existsById(productId)) {
				FixedProducts product = fixedProductRepository.findById(productId).get();
				product.setProductQuantity(quantity);
				return fixedProductRepository.save(product);
			} else {
				throw new RationApplicationException(productId + " Given product Id is Not Found");
			}
		} else {
			throw new RationApplicationException(rationAdminId + " Given RationAdmin Id is Not Found");
		}

	}

	@Override
	@Cascade(CascadeType.DELETE)
	public String deleteFixedProduct(int rationAdminId, int productId) throws RationApplicationException {
		if (rationAdminRepository.existsById(rationAdminId)) {
			fixedProductRepository.deleteById(productId);
			return "Deleted successfully";

		} else {
			throw new RationApplicationException("Admin Id is incorrect");
		}
	}

	@Override
	public Address addAddress(Address address, int rationId) throws RationApplicationException {
		RationCard rationCard2 = rationCardRepository.findById(rationId).get();
		if (address != null) {
			rationCard2.setAddresses(address);
			address.setRationCard(rationCard2);
			rationCardRepository.save(rationCard2);

			return addressRepository.save(address);
		} else {
			throw new RationApplicationException("New address Can't able to create");
		}
	}

	@Override
	public RationCard readRationCard(long rationCardNo) throws RationApplicationException {
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNo);
		return rationCard;
	}

	@Override
	public List<OrderDetails> readOrderBymonth(int month) throws RationApplicationException {
		List<OrderDetails> list = orderRepository.findAll();
		List<OrderDetails> list2 = new ArrayList<OrderDetails>();
		if (month > 0 && month <= 12) {
			for (OrderDetails orderDetails : list) {
				if (orderDetails.getLocalDate().getMonthValue() == month) {
					list2.add(orderDetails);
				}
			}

		} else {
			throw new RationApplicationException("Enter the Correct Month Value");
		}
		return list2;

	}

	@Override
	public List<RationCardMember> addListRationCardMember(List<RationCardMember> rationCardMember, long rationCardNo)
			throws RationApplicationException {
		
		
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNo);
		List<RationCardMember> rationCardMembers=new ArrayList<RationCardMember>();
		
		for (RationCardMember rationCardMember2 : rationCardMember) {
			rationCardMember2.setRationCard(rationCard);
		}
		if (rationCardMember != null) {
			//rationCardMember2.setRationCard(rationCard);
			return rationCardMemberRepository.saveAll(rationCardMember);
		} else {
			throw new RationApplicationException("can't able to create");
		}
	
		   
		
	}
}
