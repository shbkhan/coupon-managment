package com.shehab.couponmngmnt.model.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicableCouponModel {
	private Long id;
	private String type;
	private Double discount;
	private String key;
}
