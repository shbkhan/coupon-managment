package com.shehab.couponmngmnt.model.api;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BxGyCouponApiModel extends MstCouponApiModel{

	private String id;
	private String description;
	private String isActive;
	private Timestamp expirationDate;
	private List<BuyGetProductApiModel> buyProduct;
	private List<BuyGetProductApiModel> getProduct;
	private int repitationLimit;
	private int buyQuantity;
	private int getQuantity;
	
}
