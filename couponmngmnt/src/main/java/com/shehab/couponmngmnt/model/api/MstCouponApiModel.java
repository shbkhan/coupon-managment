package com.shehab.couponmngmnt.model.api;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @JsonSubTypes.Type(value = ProductWiseCouponApiModel.class, name = "product_wise"),
		@JsonSubTypes.Type(value = CartWiseCouponApiModel.class, name = "cart_wise"),
		@JsonSubTypes.Type(value = BxGyCouponApiModel.class, name = "bxgy")})
public abstract class MstCouponApiModel {
	@NotNull
	private String type;
}
