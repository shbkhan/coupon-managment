package com.shehab.couponmngmnt.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "T_BXGY_DETAILS")
@Entity
public class BxGyCouponEntity {

	@Id
	@Column(name = "ID")
	private String id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "COUPON_ID")
	private MstCouponEntity mstCouponEntity;
	
	@Column(name = "REPETITION_LIMIT")
	private int repitationLimit;
	
	@Column(name = "BUY_QUANTITY")
	private int buyQuantity;
	
	@Column(name = "GET_QUANTITY")
	private int getQuantity;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bxGyCouponEntity", targetEntity = BxGyProductsArrayEntity.class)
	private List<BxGyProductsArrayEntity> bxGyProductsArrayEntity;
	
}
