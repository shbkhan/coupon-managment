package com.shehab.couponmngmnt.model.returnmodel;

import java.util.List;

import com.shehab.couponmngmnt.model.ProductModel;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdatedCartReturnModel {

	private List<ProductModel> productModel;
	private Double totalPrice;
	private Double totalDiscount;
	private Double finalPrice;
}
