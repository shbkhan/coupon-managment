package com.shehab.couponmngmnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shehab.couponmngmnt.entity.BxGyProductsArrayEntity;

@Repository
public interface IBxGyProductsArrayRepo extends JpaRepository<BxGyProductsArrayEntity, Long>{

}
