package com.shehab.couponmngmnt.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shehab.couponmngmnt.constants.GlobalConstants;
import com.shehab.couponmngmnt.model.api.MstCouponApiModel;
import com.shehab.couponmngmnt.model.returnmodel.MstCouponReturnModel;
import com.shehab.couponmngmnt.service.IDataService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/coupons")
@Slf4j
public class CouponController {

	@Autowired
	private IDataService dataService;

	@Operation(summary = "Endpoint to create a new coupon")
	@PostMapping
	public ResponseEntity<?> createCoupon(@RequestBody MstCouponApiModel model) {
		log.info("The model is as : ---> " + model.toString());
		if(Objects.isNull(model)) {
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalConstants.EMPTY_REQUEST_BODY);
		}
		
		MstCouponApiModel res = this.dataService.createCoupon(model);

		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	@GetMapping
	public ResponseEntity<?> getAllCoupon() {
		List<MstCouponReturnModel> res = this.dataService.getAllCoupon();
		if(Objects.isNull(res) || res.size() == 0) {
			ResponseEntity.status(HttpStatus.NO_CONTENT).body(GlobalConstants.COUPON_NOT_PRESENT);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "pass the coupon id/coupon code to get the details")
	public ResponseEntity<?> getCouponById(@PathVariable("id") Long id) {
		if(id == 0L){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalConstants.ID_CANNOT_BE_NULL);
		}
		MstCouponReturnModel res = this.dataService.getCouponById(id);
		if(Objects.isNull(res)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalConstants.COUPON_NOT_PRESENT);
		}
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "update a coupon by coupon id ")
	public ResponseEntity<?> updateCouponById(@PathVariable("id") Long id, @RequestBody MstCouponApiModel model) {
		log.info("The model is as :------>"+model.toString());
		if(id == 0L || Objects.isNull(model)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(GlobalConstants.ID_CANNOT_BE_NULL+" "+GlobalConstants.EMPTY_REQUEST_BODY);
		}
		
		MstCouponApiModel res = this.dataService.updateCouponById(id,model);
		if(Objects.isNull(res)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalConstants.COUPON_NOT_PRESENT);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	@DeleteMapping("/{id}")
	@Operation(summary = "delete a coupon by coupon id")
	public ResponseEntity<?> deleteCouponById(@PathVariable("id") Long id) {
		int res = this.dataService.deleteCouponById(id);
		if(res == -1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalConstants.COUPON_NOT_PRESENT);
		}
		if(res == 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(GlobalConstants.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(GlobalConstants.DELETED_SUCCESSFULLY);
	}
}
