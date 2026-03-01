package com.shehab.couponmngmnt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shehab.couponmngmnt.entity.ProductWiseDetailEntity;

@Repository
public interface IProductWiseDetailRepo extends JpaRepository<ProductWiseDetailEntity, String>{

}
