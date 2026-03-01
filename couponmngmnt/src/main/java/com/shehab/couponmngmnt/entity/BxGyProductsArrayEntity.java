package com.shehab.couponmngmnt.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "T_BXGY_PRODUCTS_ARRAY")
@Entity
public class BxGyProductsArrayEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@JoinColumn(name = "BXGY_DETAIL_ID")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private BxGyCouponEntity bxGyCouponEntity;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "ROLE")
	private String role;
}
