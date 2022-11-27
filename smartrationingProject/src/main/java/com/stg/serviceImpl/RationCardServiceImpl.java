package com.stg.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.persistence.EnumType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.stereotype.Service;

import com.stg.entity.CardType;
import com.stg.entity.FixedProducts;
import com.stg.entity.OrderDetails;
import com.stg.entity.OrderStatus;
import com.stg.entity.Product;
import com.stg.entity.RationAdmin;
import com.stg.entity.RationCard;

import com.stg.exception.RationApplicationException;
import com.stg.repository.FixedProductRepository;
import com.stg.repository.OrderRepository;
import com.stg.repository.ProductRepository;
import com.stg.repository.RationAdminRepository;
import com.stg.repository.RationCardRepository;
import com.stg.service.RationCardService;

@Service
public class RationCardServiceImpl implements RationCardService {

	@Autowired
	private RationCardRepository rationCardRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private RationAdminRepository rationAdminRepository;
	@Autowired
	private FixedProductRepository fixedProductRepository;

	@Override
	public RationCard addRationCard(RationCard rationCard, int rationAdminId) throws RationApplicationException {
		RationAdmin rationAdmin = rationAdminRepository.findById(rationAdminId).get();
		rationCard.setRationAdmin(rationAdmin);
		return rationCardRepository.save(rationCard);
//		} else {
//			throw new RationApplicationException("given details is not sufficient");
//		}
	}

//	@Override
//	public RationCard readRationCard(int rationId) throws RationApplicationException {
//		if (rationCardRepository.existsById(rationId)) {
//			return rationCardRepository.findById(rationId).get();
//		}
//		throw new RationApplicationException(rationId + "The given Id is Not found");
//	}

//	@Override
//	public RationCard updatePassword(long rationCardNo, String password) throws RationApplicationException {
//		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNo);
//		if (rationCard != null) {
//			rationCard.setPassword(password);
//			rationCardRepository.save(rationCard);
//			return rationCard;
//		} else {
//			throw new RationApplicationException(rationCardNo + "Unable to Update");
//		}
//
//	}

//	@Override
//	public String deleteRationCard(long rationCardNo) throws RationApplicationException {
//		if (rationCardRepository.findByRationCardNo(rationCardNo) != null) {
//			rationCardRepository.deleteByRationCardNo(rationCardNo);
//			return "Deleted Successfully";
//		} else {
//			throw new RationApplicationException(rationCardNo + "Unable to Delete");
//		}
//
//	}

	// Order Ration
	@Override
	public String orderRation(long rationCardNumber) throws RationApplicationException {
		String messege = "";
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNumber);
		OrderDetails order = new OrderDetails();
		List<OrderDetails> orderDetails = orderRepository.findAll();
		boolean dateCheck = true;

		if (rationCard.getRationCardNo() == rationCardNumber) {
			for (OrderDetails orderDetails2 : orderDetails) {
				if (orderDetails2.getRationNo() == rationCardNumber) {
					if (orderDetails2.getLocalDate().getMonthValue() == LocalDate.now().getMonthValue()) {

						dateCheck = false;

						break;
					}

				}
			}
		} else {
			messege = "Invalid Ration Card Number";
		}

		List<Product> products = new ArrayList<Product>();
		products = productRepository.findAll();
		List<FixedProducts> fixedProducts = fixedProductRepository.findAll();
		float totalPrice = 0;
		List<FixedProducts> orderedProducts=new ArrayList<>();
		if (dateCheck == true) {
			for (FixedProducts fixedProducts2 : fixedProducts) {

				if ((rationCard.getType()).equals(fixedProducts2.getCardType())) {

					for (Product product : products) {
						if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName())
								&& product.getQuantity() >= 10) {
							product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());

							totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
							orderedProducts.add(fixedProducts2);
						}
//					if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName()) && product.getQuantity() >= 5) {
//						product.setQuantity(product.getQuantity()-fixedProducts2.getProductQuantity());
//						totalPrice += product.getProductPrice()*fixedProducts2.getProductQuantity();
//					}
//					if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName()) && product.getQuantity() >= 1) {
//						product.setQuantity(product.getQuantity()- fixedProducts2.getProductQuantity() );
//						totalPrice += product.getProductPrice()*fixedProducts2.getProductQuantity();
//					}
//					if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName()) && product.getQuantity() >= 2) {
//						product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());
//						totalPrice += product.getProductPrice()*fixedProducts2.getProductQuantity();
//					}
//
//					if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName()) && product.getQuantity() >= 2) {
//						product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());
//						totalPrice += product.getProductPrice()*fixedProducts2.getProductQuantity();
//					}

					}
					order.setRationNo(rationCard.getRationCardNo());
					order.setLocalDate(LocalDate.now());
					order.setPrice(totalPrice);
					order.setRationCard(rationCard);
					order.setFixedProduct(orderedProducts);
					order.setRationAdmin(rationCard.getRationAdmin());
					order.setOrderStatus(OrderStatus.PRODUCT_ORDERED);
					orderRepository.save(order);
					productRepository.saveAll(products);

					messege = LocalDate.now() + " Your order is placed Successfully!!";
				}
				// float totalPrice1 = 0;
				else if ((rationCard.getType()).equals(fixedProducts2.getCardType())) {
					products = productRepository.findAll();

					for (Product product : products) {

						if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName())
								&& product.getQuantity() >= 5) {
							product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());
							totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
							orderedProducts.add(fixedProducts2);
						}
//					if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName()) && product.getQuantity() >= 3) {
//						product.setQuantity(product.getQuantity() -fixedProducts2.getProductQuantity());
//						totalPrice += product.getProductPrice()*fixedProducts2.getProductQuantity();
//					}
//					if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName()) && product.getQuantity() >= 1) {
//						product.setQuantity(product.getQuantity()-fixedProducts2.getProductQuantity());
//						totalPrice += product.getProductPrice()*fixedProducts2.getProductQuantity();
//					}
//					if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName())) {
//						product.setQuantity(product.getQuantity()-fixedProducts2.getProductQuantity());
//						totalPrice += product.getProductPrice()*fixedProducts2.getProductQuantity();
//					}
//
//					if (product.getProductName().equalsIgnoreCase(fixedProducts2.getProductName())) {
//						product.setQuantity(product.getQuantity() -fixedProducts2.getProductQuantity());
//						totalPrice += product.getProductPrice()*fixedProducts2.getProductQuantity();
//					}

					}
					order.setRationNo(rationCard.getRationCardNo());
					order.setLocalDate(LocalDate.now());
					order.setOrderStatus(OrderStatus.PRODUCT_ORDERED);
					order.setPrice(totalPrice);
					order.setFixedProduct(orderedProducts);
					order.setRationCard(rationCard);
					order.setRationAdmin(rationCard.getRationAdmin());
					orderRepository.save(order);
					messege = " Successfullyy Ordered  " + LocalDate.now();
					productRepository.saveAll(products);
				}
			}
		}

		else {
			throw new RationApplicationException("You have Already purchased In this Month ");
		}
		return messege;

	}

	// Required Product

	@Override
	public String orderRequiredProduct(long rationCardNumber, String rice, String wheat, String oil, String sugar,
			String dhal) throws RationApplicationException {

		String messege = "";
		if(rice.equalsIgnoreCase(wheat)||rice.equalsIgnoreCase(oil)||rice.equalsIgnoreCase(sugar)||rice.equalsIgnoreCase(dhal)||
				wheat.equalsIgnoreCase(oil)||wheat.equalsIgnoreCase(sugar)||wheat.equalsIgnoreCase(dhal)||oil.equalsIgnoreCase(sugar)||
				oil.equalsIgnoreCase(dhal)||sugar.equalsIgnoreCase(dhal))
				 {
			throw new RationApplicationException("Same product Cannot able to buy");
		}else {
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNumber);
		OrderDetails order = new OrderDetails();
		List<OrderDetails> orderDetails = orderRepository.findAll();
		List<FixedProducts> fixedProducts = fixedProductRepository.findAll();
		boolean dateCheck = true;

		if (rationCard.getRationCardNo() == rationCardNumber) {
			for (OrderDetails orderDetails2 : orderDetails) {
				if (orderDetails2.getRationNo() == rationCardNumber) {
					if (orderDetails2.getLocalDate().getMonthValue() == LocalDate.now().getMonthValue()) {
						dateCheck = false;
						break;
					}

				}
			}
		} else {
			messege = "Invalid Ration Card Number";
		}
		int reduceQuantity = 0;
		float totalPrice = 0;

		List<Product> products = new ArrayList<Product>();
		List<FixedProducts> orderedProducts=new ArrayList<>();
		if (dateCheck == true) {
			for (FixedProducts fixedProducts2 : fixedProducts) {
				if ((rationCard.getType()).equals(fixedProducts2.getCardType())) {
					products = productRepository.findAll();

					for (Product product : products) {
						if (fixedProducts2.getProductName().equalsIgnoreCase(rice)) {
							if (product.getProductName().equalsIgnoreCase(rice)) {
								  if(product.getQuantity()>0) {
								reduceQuantity = product.getQuantity() - fixedProducts2.getProductQuantity();
								totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
								product.setQuantity(reduceQuantity);
								orderedProducts.add(fixedProducts2);
							}
								  else {
		                            	 throw new RationApplicationException(product.getProductName()+" Out of stock");
		                             }
							}
						}
						if (fixedProducts2.getProductName().equalsIgnoreCase(wheat)) {
							if (product.getProductName().equalsIgnoreCase(wheat)) {
								 if(product.getQuantity()>0) {
								reduceQuantity = product.getQuantity() - fixedProducts2.getProductQuantity();
								totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
								product.setQuantity(reduceQuantity);
								orderedProducts.add(fixedProducts2);
							}
								 else {
	                            	 throw new RationApplicationException(product.getProductName()+" Out of stock");
	                             }
							}
						}
						if (fixedProducts2.getProductName().equalsIgnoreCase(oil)) {
							if (product.getProductName().equalsIgnoreCase(oil)) {
								 if(product.getQuantity()>0) {
								reduceQuantity = product.getQuantity() - fixedProducts2.getProductQuantity();
								totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
								product.setQuantity(reduceQuantity);
								orderedProducts.add(fixedProducts2);
							}
								 else {
	                            	 throw new RationApplicationException(product.getProductName()+" Out of stock");
	                             }	 
							}
						}
						if (fixedProducts2.getProductName().equalsIgnoreCase(sugar)) {
							if (product.getProductName().equalsIgnoreCase(sugar)) {
								if(product.getQuantity()>0) {
								reduceQuantity = product.getQuantity() - fixedProducts2.getProductQuantity();
								totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
								product.setQuantity(reduceQuantity);
								orderedProducts.add(fixedProducts2);
							}
								 else {
	                            	 throw new RationApplicationException(product.getProductName()+" Out of stock");
	                             }	
								
							}
						}
						if (fixedProducts2.getProductName().equalsIgnoreCase(dhal)) {
							if (product.getProductName().equalsIgnoreCase(dhal)) {
								if(product.getQuantity()>0) {
								reduceQuantity = product.getQuantity() - fixedProducts2.getProductQuantity();
								totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
								product.setQuantity(reduceQuantity);
								orderedProducts.add(fixedProducts2);
							}
								 else {
	                            	 throw new RationApplicationException(product.getProductName()+" Out of stock");
	                             }	
						}

					}
					order.setRationNo(rationCard.getRationCardNo());
					order.setLocalDate(LocalDate.now());
					order.setOrderStatus(OrderStatus.PRODUCT_ORDERED);
					order.setPrice(totalPrice);
					order.setFixedProduct(orderedProducts);
					order.setRationCard(rationCard);
					order.setRationAdmin(rationCard.getRationAdmin());
					orderRepository.save(order);
					productRepository.saveAll(products);

					messege = LocalDate.now() + " Successfully Ordered !!";
				}
//				} else if ((rationCard.getType()).equals(fixedProducts2.getCardType())) {
//					products = productRepository.findAll();
//					float totalPrice1 = 0;
//					for (Product product : products) {
//
//						if (rice.equalsIgnoreCase(fixedProducts2.getProductName())) {
//							product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());
//							totalPrice1 += product.getProductPrice() * fixedProducts2.getProductQuantity();
//						}
//						if (wheat.equalsIgnoreCase(fixedProducts2.getProductName())) {
//							product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());
//							totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
//						}
//						if (oil.equalsIgnoreCase(fixedProducts2.getProductName())) {
//							product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());
//							totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
//						}
//
//						if (sugar.equalsIgnoreCase(fixedProducts2.getProductName())) {
//							product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());
//							totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
//						}
//						if (dhal.equalsIgnoreCase(fixedProducts2.getProductName())) {
//							product.setQuantity(product.getQuantity() - fixedProducts2.getProductQuantity());
//							totalPrice += product.getProductPrice() * fixedProducts2.getProductQuantity();
//						}

//					order.setRationNo(rationCard.getRationCardNo());
//					order.setLocalDate(LocalDate.now());
//					order.setOrderStatus(OrderStatus.PRODUCT_ORDERED);
//					order.setPrice(totalPrice1);
//					order.setRationCard(rationCard);
//					order.setRationAdmin(rationCard.getRationAdmin());
//					orderRepository.save(order);
//					messege = LocalDate.now() + " Successfully Ordered !!";
//					productRepository.saveAll(products);
			}
			}
		} else {
			throw new RationApplicationException(" You have Already purchased In this Month ");
		}
		}
		return messege;
		
	}

	// ============================OrderQuantity=======================================//

//	@Override
//	public String orderRequiredQuantity(long rationCardNumber, int rice, int wheat, int oil, int sugar, int dhal)
//			throws RationApplicationException {
//		String messege = "";
//		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNumber);
//		OrderDetails order = new OrderDetails();
//		List<OrderDetails> orderDetails = orderRepository.findAll();
//		boolean dateCheck = true;
//
//		if (rationCard.getRationCardNo() == rationCardNumber) {
//			for (OrderDetails orderDetails2 : orderDetails) {
//				if (orderDetails2.getRationNo() == rationCardNumber) {
//					if (orderDetails2.getLocalDate().getMonthValue() == LocalDate.now().getMonthValue()) {
//						dateCheck = false;
//						break;
//					}
//
//				}
//			}
//		} else {
//			messege = "Invalid Ration Card Number";
//		}
//		List<FixedProducts> fixedProducts = fixedProductRepository.findAll();
//		List<Product> products = new ArrayList<Product>();
//		if (dateCheck == true) {
//			for (FixedProducts fixedProducts1 : fixedProducts) {
//
//				if ((rationCard.getType()).equals(fixedProducts1.getCardType())) {
//					products = productRepository.findAll();
//					float totalPrice = 0;
//					for (Product product : products) {
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 10) {
//							if (rice >= 0 && rice <= 10) {
//								product.setQuantity(product.getQuantity() - rice);
//								totalPrice += product.getProductPrice() * rice;
//							}
//						}
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 5) {
//							if (wheat >= 0 && wheat <= 5) {
//								product.setQuantity(product.getQuantity() - wheat);
//								totalPrice += product.getProductPrice();
//							}
//						}
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 1) {
//							if (oil >= 0 && oil <= 1) {
//								product.setQuantity(product.getQuantity() - oil);
//								totalPrice += product.getProductPrice();
//							}
//						}
//
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 2) {
//							if (sugar >= 0 && sugar <= 2) {
//								product.setQuantity(product.getQuantity() - sugar);
//								totalPrice += product.getProductPrice();
//							}
//						}
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 2) {
//							if (dhal >= 0 && dhal <= 2) {
//								product.setQuantity(product.getQuantity() - dhal);
//								totalPrice += product.getProductPrice();
//							}
//						}
//
//					}
//					order.setRationNo(rationCard.getRationCardNo());
//					order.setLocalDate(LocalDate.now());
//					order.setPrice(totalPrice);
//					order.setOrderStatus(OrderStatus.PRODUCT_ORDERED);
//					order.setRationAdmin(rationCard.getRationAdmin());
//					orderRepository.save(order);
//					productRepository.saveAll(products);
//
//					messege = LocalDate.now()
//							+ "Successfully Ordered You can collect Your products Within one week  from your order Date";
//				} else if ((rationCard.getType()).equals(CardType.PHH)) {
//					products = productRepository.findAll();
//					float totalPrice = 0;
//					for (Product product : products) {
//
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 5) {
//							if (rice >= 0 && rice <= 5) {
//								product.setQuantity(product.getQuantity() - rice);
//								totalPrice += product.getProductPrice();
//							}
//						}
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 3) {
//							if (wheat >= 0 && wheat <= 3) {
//								product.setQuantity(product.getQuantity() - wheat);
//								totalPrice += product.getProductPrice();
//							}
//						}
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 1) {
//							if (oil >= 0 && oil <= 1) {
//								product.setQuantity(product.getQuantity() - oil);
//								totalPrice += product.getProductPrice();
//							}
//						}
//
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 1) {
//							if (sugar >= 0 && sugar <= 1) {
//								product.setQuantity(product.getQuantity() - sugar);
//								totalPrice += product.getProductPrice();
//							}
//						}
//						if (product.getProductName().equalsIgnoreCase(fixedProducts1.getProductName())
//								&& product.getQuantity() >= 1) {
//							if (dhal >= 0 && dhal <= 1) {
//								product.setQuantity(product.getQuantity() - dhal);
//								totalPrice += product.getProductPrice();
//							}
//
//						}
//						order.setRationNo(rationCard.getRationCardNo());
//						order.setLocalDate(LocalDate.now());
//						order.setPrice(totalPrice);
//						order.setRationAdmin(rationCard.getRationAdmin());
//						orderRepository.save(order);
//						order.setOrderStatus(OrderStatus.PRODUCT_ORDERED);
//						messege = LocalDate.now()
//								+ "Successfully Ordered You can collect Your products Within one week  from your order Date";
//						productRepository.saveAll(products);
//					}
//				}
//
//				else {
//					throw new RationApplicationException(" You have Already purchased In this Month ");
//				}
//
//			}
//
//		}
//		return messege;
//	}

	// required Quantity====================
//	public String purchaseeByQuantity(String rice) {
//		// rice = "rice 20";
//		String[] strArr = rice.split(" ");
//		int riceQuantity = Integer.valueOf(strArr[1]);
//		List<Product> products = productRepository.findAll();
//
//		int finalQuantity = 0;
//
//		for (Product product : products) {
//			if (product.getProductName().equalsIgnoreCase(strArr[0])) {
//				finalQuantity = product.getQuantity() - riceQuantity;
//				product.setQuantity(finalQuantity);
//			}
//		}
//
//		productRepository.saveAll(products);
//		// }
//		return "returned";
//
//	}

	@Override
	public String orderRequiredQuantity1(long rationCardNumber, String rice, String wheat, String oil, String sugar,
			String dhal) throws RationApplicationException {

		String messege = "";
		if(rice.equalsIgnoreCase(wheat)||rice.equalsIgnoreCase(oil)||rice.equalsIgnoreCase(sugar)||rice.equalsIgnoreCase(dhal)||
				wheat.equalsIgnoreCase(oil)||wheat.equalsIgnoreCase(sugar)||wheat.equalsIgnoreCase(dhal)||oil.equalsIgnoreCase(sugar)||
				oil.equalsIgnoreCase(dhal)||sugar.equalsIgnoreCase(dhal))
				 {
			throw new RationApplicationException("Same product Cannot able to buy");
		}else {
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNumber);
		OrderDetails order = new OrderDetails();
		List<OrderDetails> orderDetails = orderRepository.findAll();
		boolean dateCheck = true;

		String[] rice1 = rice.split(" ");
		int riceQuantity = Integer.valueOf(rice1[1]);
		String[] wheat1 = wheat.split(" ");
		int wheatQuantity = Integer.valueOf(wheat1[1]);
		String[] oil1 = oil.split(" ");
		int oilQuantity = Integer.valueOf(oil1[1]);
		String[] sugar1 = sugar.split(" ");
		int sugarQuantity = Integer.valueOf(sugar1[1]);
		String[] dhal1 = dhal.split(" ");
		int dhalQuantity = Integer.valueOf(dhal1[1]);
		List<Product> products = productRepository.findAll();

		if (rationCard.getRationCardNo() == rationCardNumber) {
			for (OrderDetails orderDetails2 : orderDetails) {
				if (orderDetails2.getRationNo() == rationCardNumber) {
					if (orderDetails2.getLocalDate().getMonthValue() == LocalDate.now().getMonthValue()) {
						dateCheck = false;
						break;
					}

				}
			}
		} else {
			messege = "Invalid Ration Card Number";
		}

		int finalQuantity = 0;
		float totalPrice = 0;
		int riceCheck = 0;
		int wheatCheck = 0;
		int oilCheck = 0;
		int sugarCheck = 0;
		int dhalCheck = 0;
		List<FixedProducts> fixedProducts = fixedProductRepository.findAll();
		if (dateCheck == true) {
			for (FixedProducts fixedProducts2 : fixedProducts) {

				if (rationCard.getType().equals(CardType.NPHH)
						&& rationCard.getType().equals(fixedProducts2.getCardType())) {
					for (Product product : products) {
						if (product.getProductName().equalsIgnoreCase(rice1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(rice1[0])) {
							if (riceQuantity >= 0 && riceQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - riceQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * riceQuantity;
								riceCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type rice Quantity  ");
							}
						}
						if (product.getProductName().equalsIgnoreCase(wheat1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(wheat1[0])) {
							if (wheatQuantity >= 0 && wheatQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - wheatQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * wheatQuantity;
								wheatCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type wheat Quantity  ");
							}
						}
						if (product.getProductName().equalsIgnoreCase(oil1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(oil1[0])) {
							if (oilQuantity >= 0 && oilQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - oilQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * oilQuantity;
								oilCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type oil Quantity  ");
							}
						}
						if (product.getProductName().equalsIgnoreCase(sugar1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(sugar1[0])) {
							if (sugarQuantity >= 0 && sugarQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - sugarQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * sugarQuantity;
								sugarCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type sugar Quantity  ");
							}
						}
						if (product.getProductName().equalsIgnoreCase(dhal1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(dhal1[0])) {
							if (dhalQuantity >= 0 && dhalQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - dhalQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * dhalQuantity;
								dhalCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type dhal Quantity ");
							}

						}
						if (riceCheck > 0 || wheatCheck > 0 || oilCheck > 0 || sugarCheck > 0 || dhalCheck > 0) {
							order.setRationNo(rationCard.getRationCardNo());
							order.setLocalDate(LocalDate.now());
							order.setPrice(totalPrice);
							order.setRationAdmin(rationCard.getRationAdmin());
							order.setRationCard(rationCard);
							order.setOrderStatus(OrderStatus.PRODUCT_ORDERED);
							orderRepository.save(order);

							messege = LocalDate.now() + " Successfully Ordered !!";
						}
					}
				} else if (rationCard.getType().equals(CardType.PHH)
						&& rationCard.getType().equals(fixedProducts2.getCardType())) {
					for (Product product : products) {
						if (product.getProductName().equalsIgnoreCase(rice1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(rice1[0])) {
							if (riceQuantity >= 0 && riceQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - riceQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * riceQuantity;
								riceCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type rice Quantity  ");
							}
						}
						if (product.getProductName().equalsIgnoreCase(wheat1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(wheat1[0])) {
							if (wheatQuantity >= 0 && wheatQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - wheatQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * wheatQuantity;
								wheatCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type wheat Quantity  ");
							}
						}
						if (product.getProductName().equalsIgnoreCase(oil1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(oil1[0])) {
							if (oilQuantity >= 0 && oilQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - oilQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * oilQuantity;
								oilCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type oil Quantity  ");
							}
						}
						if (product.getProductName().equalsIgnoreCase(sugar1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(sugar1[0])) {
							if (sugarQuantity >= 0 && sugarQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - sugarQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * sugarQuantity;
								sugarCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type sugar Quantity  ");
							}
						}
						if (product.getProductName().equalsIgnoreCase(dhal1[0])
								&& fixedProducts2.getProductName().equalsIgnoreCase(dhal1[0])) {
							if (dhalQuantity >= 0 && dhalQuantity <= fixedProducts2.getProductQuantity()) {
								finalQuantity = product.getQuantity() - dhalQuantity;
								product.setQuantity(finalQuantity);
								totalPrice += product.getProductPrice() * dhalQuantity;
								dhalCheck++;
							} else {
								throw new RationApplicationException(
										"Your Card is eligible only for Your Card Type dhal Quantity  ");
							}
						}

					}
					if (riceCheck > 0 || wheatCheck > 0 || oilCheck > 0 || sugarCheck > 0 || dhalCheck > 0) {
						order.setRationNo(rationCard.getRationCardNo());
						order.setLocalDate(LocalDate.now());
						order.setPrice(totalPrice);
						order.setRationAdmin(rationCard.getRationAdmin());
						order.setRationCard(rationCard);
						order.setOrderStatus(OrderStatus.PRODUCT_ORDERED);
						orderRepository.save(order);

						messege = LocalDate.now() + " Successfully Ordered !!";
					}
				}
			}
		} else {
			throw new RationApplicationException(" You have Already purchased In this Month ");
		}
		}
		return messege;
	}

	@Override
	public List<OrderDetails> readOrders(long rationCardNumber) throws RationApplicationException {
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNumber);
		OrderDetails orderDetails = orderRepository.findByRationNo(rationCardNumber);
		List<OrderDetails> list = new ArrayList<OrderDetails>();
		if (orderDetails.getRationNo() == rationCardNumber) {
			list.add(orderDetails);
		} else {
			throw new RationApplicationException("You have entered wrong rationCardNumber ");
		}
		return list;

	}

	@Override
	public List<FixedProducts> readfixedProduct(long rationCardNumber) throws RationApplicationException {
		RationCard rationCard = rationCardRepository.findByRationCardNo(rationCardNumber);
		List<FixedProducts> fixedProducts = fixedProductRepository.findAll();
		List<FixedProducts> products = new ArrayList<FixedProducts>();
		for (FixedProducts fixedProducts2 : fixedProducts) {
			if (rationCard.getType().equals(fixedProducts2.getCardType())) {
				products.add(fixedProducts2);

//			} else {
//				throw new RationApplicationException("Not Obtained");
//			}
			}

		}
		return products;
	}

	@Override
	public String findByProductRice(String productName) throws RationApplicationException {
		
			Product product = productRepository.findByProductName(productName);
			if (product.getQuantity() > 0) {
				return productName + " is Available";
			} else {
				throw new RationApplicationException(productName + " is Not Available");
			}
		
		
	}
}
