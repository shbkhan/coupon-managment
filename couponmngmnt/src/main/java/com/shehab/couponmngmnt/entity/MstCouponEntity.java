package com.shehab.couponmngmnt.entity;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "T_MST_COUPONS")
@Entity
@Data
@NoArgsConstructor
public class MstCouponEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IS_ACTIVE")
	private String isActive;

	@Column(name = "EXPIRATION_DATE")
	private Timestamp expirationDate;

	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "UPDATED_AT")
	private Timestamp updatedAt;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "mstCouponEntity", cascade = CascadeType.ALL, targetEntity = CartWiseDetailEntity.class)
	private CartWiseDetailEntity cartWiseDetailEntity;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "mstCouponEntity", cascade = CascadeType.ALL, targetEntity = ProductWiseDetailEntity.class)
	private ProductWiseDetailEntity productWiseDetailEntity;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "mstCouponEntity", cascade = CascadeType.ALL, targetEntity = BxGyCouponEntity.class)
	private BxGyCouponEntity bxGyCouponEntity;
}
