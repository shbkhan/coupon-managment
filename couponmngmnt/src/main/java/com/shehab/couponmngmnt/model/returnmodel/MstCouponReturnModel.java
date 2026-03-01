package com.shehab.couponmngmnt.model.returnmodel;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MstCouponReturnModel {
	private Long id;
	private String type;
	private String description;
	private String isActive;
	private Timestamp expirationDate;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	private CartWiseCouponReturnModel cartWiseReturnModel;
	private ProductWiseCouponReturnModel productWiseReturnModel;
	private BxGyCouponReturnModel bxGyCouponReturnModel;
	
}
