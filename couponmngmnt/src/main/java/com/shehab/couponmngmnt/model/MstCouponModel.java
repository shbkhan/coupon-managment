package com.shehab.couponmngmnt.model;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MstCouponModel {
	private Long id;
	private String type;
	private String description;
	private String isActive;
	private Timestamp expirationDate;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
