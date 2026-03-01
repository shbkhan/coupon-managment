package com.shehab.couponmngmnt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductModel {
	private Long productId;
//	private Double price;
	private int quantity;
	private Double totalDiscount;
}
