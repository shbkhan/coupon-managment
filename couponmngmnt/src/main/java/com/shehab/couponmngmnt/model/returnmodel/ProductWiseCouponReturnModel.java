package com.shehab.couponmngmnt.model.returnmodel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductWiseCouponReturnModel {

	private Long id;
	private Long mstCouponId;
	private Long productId;
	private Double discountPercentage;
}
