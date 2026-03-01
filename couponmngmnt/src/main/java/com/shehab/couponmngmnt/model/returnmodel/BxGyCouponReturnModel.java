package com.shehab.couponmngmnt.model.returnmodel;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BxGyCouponReturnModel {
	private String id;
	private Long mstCouponId;
	private int repitationLimit;
	private int buyQuantity;
	private int getQuantity;
	private List<BxGyProductArrayReturnModel> buyProduct;
	private List<BxGyProductArrayReturnModel> getProduct;
}
