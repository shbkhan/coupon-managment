package com.shehab.couponmngmnt.model.returnmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartWiseCouponReturnModel {

	private String id;
	private Long mstCouponId;
	private Double thresholdAmount;
	private Double discountPercentage;
}
