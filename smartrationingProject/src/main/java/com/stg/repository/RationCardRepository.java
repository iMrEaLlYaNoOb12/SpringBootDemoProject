package com.stg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.entity.RationCard;
//import com.stg.entity.RationCard;
//import com.stg.entity.RationCardUser;

@Repository
public interface RationCardRepository extends JpaRepository<RationCard, Integer> {
	@Query(value = "select * from ration_card where ration_card_no=?", nativeQuery = true)
	public abstract RationCard findByRationCardNo(long rationCardNo);

	public abstract String deleteByRationCardNo(long rationCardNo);

	


}
