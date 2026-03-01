package com.shehab.couponmngmnt.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "T_CART_WISE_DETAIL")
@Entity
@Data
@NoArgsConstructor
//@DiscriminatorColumn(name = "TYPE")
public class CartWiseDetailEntity {

	@Id
	@Column(name = "ID")
	private String id;

	@JoinColumn(name = "COUPON_ID")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private MstCouponEntity mstCouponEntity;

	@Column(name = "THRESHOLD_AMOUNT")
	private Double thresholdAmount;

	@Column(name = "DISCOUNT_PERCENTAGE")
	private Double discountPercentage;
}
