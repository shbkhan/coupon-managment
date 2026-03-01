package com.shehab.couponmngmnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shehab.couponmngmnt.entity.CartWiseDetailEntity;

@Repository
public interface ICartWiseDetailRepo extends JpaRepository<CartWiseDetailEntity, String> {

}
