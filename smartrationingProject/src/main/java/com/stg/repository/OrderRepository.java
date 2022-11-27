package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.entity.OrderDetails;

public interface OrderRepository extends JpaRepository<OrderDetails, Integer> {

	public abstract OrderDetails findByRationNo(long rationCardNo);

}
