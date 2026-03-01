package com.shehab.couponmngmnt.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shehab.couponmngmnt.model.ProductModel;
import com.shehab.couponmngmnt.model.api.ApplicableCouponModel;
import com.shehab.couponmngmnt.model.api.MstCouponApiModel;
import com.shehab.couponmngmnt.model.returnmodel.MstCouponReturnModel;
import com.shehab.couponmngmnt.model.returnmodel.UpdatedCartReturnModel;

@Component
public interface IDataService {

	public MstCouponApiModel createCoupon(MstCouponApiModel model);

	public List<MstCouponReturnModel> getAllCoupon();

	public MstCouponReturnModel getCouponById(Long id);

	public MstCouponApiModel updateCouponById(Long id, MstCouponApiModel model);

	public int deleteCouponById(Long id);

	public List<ApplicableCouponModel> getAllApplicableCoupon(List<ProductModel> product);

	public UpdatedCartReturnModel applyCoupon(Long id, List<ProductModel> product);

}
