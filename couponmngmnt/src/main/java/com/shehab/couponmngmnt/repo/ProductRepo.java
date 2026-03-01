package com.shehab.couponmngmnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shehab.couponmngmnt.entity.ProductEntity;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long>{

}
