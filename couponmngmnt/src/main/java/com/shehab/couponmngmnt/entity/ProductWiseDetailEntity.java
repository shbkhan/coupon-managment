package com.shehab.couponmngmnt.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "T_PRODUCT_WISE_DETAILS")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductWiseDetailEntity {

	@Id
	@Column(name = "ID")
	private String id;

	@JoinColumn(name = "COUPON_ID")
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private MstCouponEntity mstCouponEntity;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "DISCOUNT_PERCENTAGE")
	private Double discountPercentage;
}
