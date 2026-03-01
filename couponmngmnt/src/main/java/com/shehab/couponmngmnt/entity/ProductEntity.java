package com.shehab.couponmngmnt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Table(name = "T_PRODUCT")
@Entity
public class ProductEntity {

	@Id
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "PRICE")
	private Double price;
}
