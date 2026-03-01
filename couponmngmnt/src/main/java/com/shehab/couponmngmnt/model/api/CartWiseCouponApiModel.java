package com.shehab.couponmngmnt.model.api;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CartWiseCouponApiModel extends MstCouponApiModel {
	private String id;
	private String description;
	private String isActive;
	private Timestamp expirationDate;
	private Double thresholdAmount;
	private Double discountPercentage;

}
