package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stg.entity.Address;

public interface AddressRepository  extends JpaRepository<Address, Integer>{

      public boolean existsByArea(String area);
      
      public List<Address> findAllByArea(String area);

}
