package com.shehab.couponmngmnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shehab.couponmngmnt.entity.BxGyCouponEntity;

@Repository
public interface IBxGyCouponRepo extends JpaRepository<BxGyCouponEntity, String>{

}
