package com.shehab.couponmngmnt.model.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyGetProductApiModel {

	private Long productId;
	private String role;
}
