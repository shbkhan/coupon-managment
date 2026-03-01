package com.shehab.couponmngmnt.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.shehab.couponmngmnt.business.CouponBO;
import com.shehab.couponmngmnt.business.exception.DuplicateDataException;
import com.shehab.couponmngmnt.entity.MstCouponEntity;
import com.shehab.couponmngmnt.model.ProductModel;
import com.shehab.couponmngmnt.model.api.ApplicableCouponModel;
import com.shehab.couponmngmnt.model.api.BxGyCouponApiModel;
import com.shehab.couponmngmnt.model.api.CartWiseCouponApiModel;
import com.shehab.couponmngmnt.model.api.MstCouponApiModel;
import com.shehab.couponmngmnt.model.api.ProductWiseCouponApiModel;
import com.shehab.couponmngmnt.model.returnmodel.MstCouponReturnModel;
import com.shehab.couponmngmnt.model.returnmodel.UpdatedCartReturnModel;
import com.shehab.couponmngmnt.service.IDataService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataServiceImpl implements IDataService {

	@Autowired
	private CouponBO couponBO;

	@Override
	public MstCouponApiModel createCoupon(MstCouponApiModel model) {
		MstCouponEntity couponData = new MstCouponEntity();
		try {
			if (model instanceof ProductWiseCouponApiModel) {
				couponBO.saveProductWiseData(model, couponData);
			}
			if (model instanceof CartWiseCouponApiModel) {
				couponBO.saveCartWiseData(model, couponData);
			}
			if(model instanceof BxGyCouponApiModel) {
				couponBO.saveBxGyCouponData(model,couponData);
			}
			log.info("The coupondata is as : " + couponData.toString());
			return model;
		} catch(DataIntegrityViolationException de) {
			throw new DuplicateDataException("Duplicate coupon");
		}
	}

	public List<MstCouponReturnModel> getAllCoupon() {
		List<MstCouponReturnModel> data = couponBO.getAllCoupons();
		return data;
	}

	public MstCouponReturnModel getCouponById(Long id) {
		MstCouponReturnModel data = couponBO.getCouponById(id);
		return data;
	}
	
	public MstCouponApiModel updateCouponById(Long id, MstCouponApiModel model) {
		if(!couponBO.isCouponExist(id)) {
			return null;
		}
		try {
			MstCouponEntity couponData = new MstCouponEntity();
			if(model instanceof ProductWiseCouponApiModel) {
				couponBO.updateProductWiseData(model,couponData,id);
			}
			if(model instanceof CartWiseCouponApiModel) {
				couponBO.updateCartWiseData(model, couponData, id);
			}
			if(model instanceof BxGyCouponApiModel) {
				couponBO.updateBxGyCoupon(model, couponData, id);
			}
			return model;
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateDataException("Duplicate data");
		}
		
	}
	
	public int deleteCouponById(Long id) {
		if(!couponBO.isCouponExist(id)) {
			return -1;
		}
		return couponBO.deleteById(id);
	}
	
	public List<ApplicableCouponModel> getAllApplicableCoupon(List<ProductModel> product) {
		if(Objects.isNull(product) || product.size() == 0) {
			return null;
		}
		
		List<ApplicableCouponModel> data = couponBO.getAllApplicableCoupons(product);
		
		
		return data;
	}
	
	public UpdatedCartReturnModel applyCoupon(Long id, List<ProductModel> product) {
		if(!couponBO.isCouponExist(id)) {
			return null;
		}
		
		UpdatedCartReturnModel data = couponBO.applyCoupon(id,product);
		
		return data;
	}
}
