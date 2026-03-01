package com.shehab.couponmngmnt.model.api;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductWiseCouponApiModel extends MstCouponApiModel {
	private String id;
	private String description;
	private String isActive;
	private Timestamp expirationDate;
	private Long productId;
	private Double discountPercentage;
}
