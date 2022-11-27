package com.stg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.entity.FixedProducts;

public interface FixedProductRepository extends JpaRepository<FixedProducts, Integer> {

}
