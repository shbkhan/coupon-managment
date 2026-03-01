package com.shehab.couponmngmnt.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shehab.couponmngmnt.constants.GlobalConstants;
import com.shehab.couponmngmnt.model.ProductModel;
import com.shehab.couponmngmnt.model.api.ApplicableCouponModel;
import com.shehab.couponmngmnt.model.returnmodel.UpdatedCartReturnModel;
import com.shehab.couponmngmnt.service.IDataService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping
public class MasterController {

	@Autowired
	private IDataService dataService;
	
	@PostMapping("/applicable-coupons")
	@Operation(summary = "List of all the applicable coupons")
	public ResponseEntity<?> getAllApplicableCoupon(@RequestBody List<ProductModel> product) {
		if(Objects.isNull(product) || product.size() == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalConstants.EMPTY_REQUEST_BODY);
		}
		
		List<ApplicableCouponModel> res = this.dataService.getAllApplicableCoupon(product); 
		if(Objects.isNull(res) || res.size()==0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(GlobalConstants.NO_RESULT_FOUND);
		}
		return ResponseEntity.ok(res);
	}
	@PostMapping("/apply-coupon/{id}")
	@Operation(summary = "apply coupon")
	public ResponseEntity<?> applyCoupon(@PathVariable("id") Long id, @RequestBody List<ProductModel> product) {
		if(Objects.isNull(product) || product.size()==0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalConstants.EMPTY_REQUEST_BODY);
		}
		
		UpdatedCartReturnModel res = this.dataService.applyCoupon(id,product);
		if(Objects.isNull(res)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalConstants.COUPON_NOT_PRESENT);
		}
		
		return ResponseEntity.ok(res);
		
		
	}
}
