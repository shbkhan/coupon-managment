package com.shehab.couponmngmnt.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shehab.couponmngmnt.constants.GlobalConstants;
import com.shehab.couponmngmnt.entity.BxGyCouponEntity;
import com.shehab.couponmngmnt.entity.BxGyProductsArrayEntity;
import com.shehab.couponmngmnt.entity.CartWiseDetailEntity;
import com.shehab.couponmngmnt.entity.MstCouponEntity;
import com.shehab.couponmngmnt.entity.ProductEntity;
import com.shehab.couponmngmnt.entity.ProductWiseDetailEntity;
import com.shehab.couponmngmnt.model.ProductModel;
import com.shehab.couponmngmnt.model.api.ApplicableCouponModel;
import com.shehab.couponmngmnt.model.api.BuyGetProductApiModel;
import com.shehab.couponmngmnt.model.api.BxGyCouponApiModel;
import com.shehab.couponmngmnt.model.api.CartWiseCouponApiModel;
import com.shehab.couponmngmnt.model.api.MstCouponApiModel;
import com.shehab.couponmngmnt.model.api.ProductWiseCouponApiModel;
import com.shehab.couponmngmnt.model.returnmodel.BxGyCouponReturnModel;
import com.shehab.couponmngmnt.model.returnmodel.BxGyProductArrayReturnModel;
import com.shehab.couponmngmnt.model.returnmodel.CartWiseCouponReturnModel;
import com.shehab.couponmngmnt.model.returnmodel.MstCouponReturnModel;
import com.shehab.couponmngmnt.model.returnmodel.ProductWiseCouponReturnModel;
import com.shehab.couponmngmnt.model.returnmodel.UpdatedCartReturnModel;
import com.shehab.couponmngmnt.repo.IBxGyCouponRepo;
import com.shehab.couponmngmnt.repo.IBxGyProductsArrayRepo;
import com.shehab.couponmngmnt.repo.ICartWiseDetailRepo;
import com.shehab.couponmngmnt.repo.IMstCouponRepo;
import com.shehab.couponmngmnt.repo.IProductWiseDetailRepo;
import com.shehab.couponmngmnt.repo.ProductRepo;
import com.shehab.couponmngmnt.utility.Utils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CouponBO {

	@Autowired
	private IMstCouponRepo mstCouponRepo;

	@Autowired
	private Utils utils;

	@Autowired
	private IProductWiseDetailRepo productWiseDetailRepo;

	@Autowired
	private ICartWiseDetailRepo cartWiseDetailRepo;
	
	@Autowired
	private IBxGyProductsArrayRepo bxGyProductsArrayRepo;

	@Autowired
	private IBxGyCouponRepo bxGyCouponRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	public void createCoupon(MstCouponEntity couponData) {
		couponData.setCreatedAt(utils.getCurrentTimestamp());
		couponData.setUpdatedAt(utils.getCurrentTimestamp());
		mstCouponRepo.save(couponData);
	}

	@Transactional
	public void saveCartWiseData(MstCouponApiModel model, MstCouponEntity couponData) {
		CartWiseCouponApiModel p2 = (CartWiseCouponApiModel) model;
		BeanUtils.copyProperties(p2, couponData);
		couponData.setCreatedAt(utils.getCurrentTimestamp());
		couponData.setUpdatedAt(utils.getCurrentTimestamp());
		CartWiseDetailEntity cartWiseData = new CartWiseDetailEntity();
		BeanUtils.copyProperties(p2, cartWiseData);
		cartWiseData.setMstCouponEntity(couponData);
		log.info("----->" + cartWiseData.toString());
		cartWiseDetailRepo.save(cartWiseData);
		log.info("cart wise data has been saved in the db");
	}

	@Transactional
	public void saveProductWiseData(MstCouponApiModel model, MstCouponEntity couponData) {
		ProductWiseCouponApiModel p1 = (ProductWiseCouponApiModel) model;
		BeanUtils.copyProperties(p1, couponData);
		couponData.setCreatedAt(utils.getCurrentTimestamp());
		couponData.setUpdatedAt(utils.getCurrentTimestamp());
		ProductWiseDetailEntity productWiseData = new ProductWiseDetailEntity();
		BeanUtils.copyProperties(p1, productWiseData);
		productWiseData.setMstCouponEntity(couponData);
		log.info("----->" + productWiseData.toString());
		productWiseDetailRepo.save(productWiseData);
		log.info("product wise data has been saved in the db");
	}
	
	@Transactional
	public void saveBxGyCouponData(MstCouponApiModel model, MstCouponEntity couponData) {
		log.info("The bxgy model is as : "+model.toString());
		
		BxGyCouponApiModel p3 = (BxGyCouponApiModel) model;
		BeanUtils.copyProperties(p3, couponData);
		couponData.setCreatedAt(utils.getCurrentTimestamp());
		couponData.setUpdatedAt(utils.getCurrentTimestamp());
//		mstCouponRepo.save(couponData);
		
		BxGyCouponEntity bxGyCouponEntity = new BxGyCouponEntity();
		BeanUtils.copyProperties(p3, bxGyCouponEntity);
		bxGyCouponEntity.setMstCouponEntity(couponData);
//		bxGyCouponRepo.save(bxGyCouponEntity);
		
		
		List<BxGyProductsArrayEntity> bxGy = new ArrayList<>();
		for(BuyGetProductApiModel bxgy : p3.getBuyProduct()) {
			BxGyProductsArrayEntity temp = new BxGyProductsArrayEntity();
			BeanUtils.copyProperties(bxgy, temp);
			temp.setBxGyCouponEntity(bxGyCouponEntity);
//			bxGyProductsArrayRepo.save(temp);
			bxGy.add(temp);
		}
		for(BuyGetProductApiModel bxgy : p3.getGetProduct()) {
			BxGyProductsArrayEntity temp = new BxGyProductsArrayEntity();
			BeanUtils.copyProperties(bxgy, temp);
			temp.setBxGyCouponEntity(bxGyCouponEntity);
//			bxGyProductsArrayRepo.save(temp);
			bxGy.add(temp);
		}
		log.info("The list is as "+bxGy.toString()+" "+bxGy.size());
		bxGyProductsArrayRepo.saveAll(bxGy);
//		bxGyCouponEntity.setBxGyProductsArrayEntity(bxGy);
		
//		log.info("The data that is going to be save in db for bxgyCoupon is as :"+bxGyCouponEntity.toString());
		
	}

	public List<MstCouponReturnModel> getAllCoupons() {
		List<MstCouponEntity> data = mstCouponRepo.findAll();
		List<MstCouponReturnModel> res = new ArrayList<>();
		log.info("The size of the data is as " + data.size());
		for (MstCouponEntity ent : data) {
			MstCouponReturnModel temp = mapMstCouponReturnModel(ent);

			res.add(temp);
		}

		return res;
	}

	private MstCouponReturnModel mapMstCouponReturnModel(MstCouponEntity ent) {
		MstCouponReturnModel temp = new MstCouponReturnModel();
		BeanUtils.copyProperties(ent, temp);
		if (ent.getCartWiseDetailEntity() != null) {
			CartWiseCouponReturnModel cart = new CartWiseCouponReturnModel();
			CartWiseDetailEntity ent2 = ent.getCartWiseDetailEntity();
			BeanUtils.copyProperties(ent2, cart);
			temp.setCartWiseReturnModel(cart);

		}
		if (ent.getProductWiseDetailEntity() != null) {
			ProductWiseCouponReturnModel product = new ProductWiseCouponReturnModel();
			ProductWiseDetailEntity ent2 = ent.getProductWiseDetailEntity();
			BeanUtils.copyProperties(ent2, product);
			temp.setProductWiseReturnModel(product);
		}
		if(ent.getBxGyCouponEntity() != null) {
			BxGyCouponReturnModel bxGy = new BxGyCouponReturnModel();
			BxGyCouponEntity ent2 = ent.getBxGyCouponEntity();
			BeanUtils.copyProperties(ent2, bxGy);
			List<BxGyProductArrayReturnModel> buyProduct = new ArrayList<>();
			List<BxGyProductArrayReturnModel> getProduct = new ArrayList<>();
			List<BxGyProductsArrayEntity> lst = ent.getBxGyCouponEntity().getBxGyProductsArrayEntity();
			for(BxGyProductsArrayEntity productArray : lst) {
				BxGyProductArrayReturnModel temp3 = new BxGyProductArrayReturnModel();
				if(productArray.getRole().equals("BUY")) {
					BeanUtils.copyProperties(productArray, temp3);
					buyProduct.add(temp3);
				}
				if(productArray.getRole().equals("GET")) {
					BeanUtils.copyProperties(productArray, temp3);
					getProduct.add(temp3);
				}
			}
			bxGy.setBuyProduct(buyProduct);
			bxGy.setGetProduct(getProduct);
			temp.setBxGyCouponReturnModel(bxGy);
		}
		return temp;
	}

	public MstCouponReturnModel getCouponById(Long id) {
		MstCouponReturnModel model = new MstCouponReturnModel();
		MstCouponEntity ent = mstCouponRepo.findById(id).orElse(null);
		if(Objects.isNull(ent)) {
			return null;
		}
		model = mapMstCouponReturnModel(ent);
		return model;
	}

	public boolean isCouponExist(Long id) {
		MstCouponEntity ent = mstCouponRepo.findById(id).orElse(null);
		if(Objects.nonNull(ent)) {
			return true;
		}
		return false;
	}

	@Transactional
	public void updateProductWiseData(MstCouponApiModel model, MstCouponEntity couponData,Long id) {
		MstCouponEntity existing = mstCouponRepo.findById(id).orElse(new MstCouponEntity());

		ProductWiseCouponApiModel productModel = (ProductWiseCouponApiModel) model;
		BeanUtils.copyProperties(productModel, existing);
		
		ProductWiseDetailEntity productEnt = existing.getProductWiseDetailEntity();
		BeanUtils.copyProperties(productEnt, existing);
		productEnt.setMstCouponEntity(existing);
		productWiseDetailRepo.save(productEnt);
	}

	@Transactional
	public void updateCartWiseData(MstCouponApiModel model, MstCouponEntity couponData,Long id) {
		MstCouponEntity existing = mstCouponRepo.findById(id).orElse(new MstCouponEntity());

		CartWiseCouponApiModel cartModel = (CartWiseCouponApiModel) model;
		BeanUtils.copyProperties(cartModel, existing);
		
		CartWiseDetailEntity cartEnt = existing.getCartWiseDetailEntity();
		BeanUtils.copyProperties(cartEnt, existing);
		cartEnt.setMstCouponEntity(existing);
		cartWiseDetailRepo.save(cartEnt);
	}
	
	@Transactional
	public void updateBxGyCoupon(MstCouponApiModel model, MstCouponEntity couponData,Long id) {
		MstCouponEntity existing = mstCouponRepo.findById(id).orElse(new MstCouponEntity());

		BxGyCouponApiModel bxgyModel = (BxGyCouponApiModel) model;
		BeanUtils.copyProperties(bxgyModel, existing);
		
		BxGyCouponEntity bxgyEnt = existing.getBxGyCouponEntity();
		BeanUtils.copyProperties(bxgyEnt, existing);
		bxgyEnt.setMstCouponEntity(existing);
		
//		List<BxGyProductsArrayEntity> productArrayEnt = bxgyEnt.getBxGyProductsArrayEntity();
//
//		List<BuyGetProductApiModel> buyModel = bxgyModel.getBuyProduct();
//		update remaining
		
	}

	@Transactional
	public int deleteById(Long id) {
		try {
			mstCouponRepo.deleteById(id);
			return 1;
		} catch(Exception e) {
			return 0;
		}
	}

	public List<ApplicableCouponModel> getAllApplicableCoupons(List<ProductModel> product) {
		List<ApplicableCouponModel> data = new ArrayList<>();
		
		getCartWiseCoupon(data,product);
		getProductWiseCoupon(data,product);
		getBxGyCoupon(data,product);
		log.info("The total numbers of coupons applicable is "+data.size());
		return data;
	}
	public void getBxGyCoupon(List<ApplicableCouponModel> data,List<ProductModel> product) {
		List<ProductEntity> products = productRepo.findAll();
		//Get all productIds from the cart
		Set<Long> productIds = product.stream().map(a->a.getProductId()).collect(Collectors.toSet());
		
		//Get all entries from db valid form the product Ids in the cart
		List<MstCouponEntity> coupon = mstCouponRepo.findApplicableBxGyCouponInProductId(productIds).orElse(new ArrayList<>());
		
		for(MstCouponEntity ent : coupon) {
			ApplicableCouponModel temp = new ApplicableCouponModel();
			List<BxGyProductsArrayEntity> lstBxGyProductArrayData = ent.getBxGyCouponEntity().getBxGyProductsArrayEntity();
			
			Set<Long> productIdBuy = lstBxGyProductArrayData.stream().filter(a->a.getRole().equals("BUY")).map(a->a.getProductId()).collect(Collectors.toSet());
			Set<Long> productIdGet = lstBxGyProductArrayData.stream().filter(a->a.getRole().equals("GET")).map(a->a.getProductId()).collect(Collectors.toSet());
			
			int buyQuantityTarget = ent.getBxGyCouponEntity().getBuyQuantity();
			int getQuantity = ent.getBxGyCouponEntity().getGetQuantity();
			
			if(hasCartRequiredProduct(productIds, productIdBuy, buyQuantityTarget) && 
					hasCartRequiredProduct(productIds, productIdGet, getQuantity)) {
				temp.setId(ent.getId());
				temp.setKey(ent.getBxGyCouponEntity().getId());
				temp.setType(ent.getType());
				temp.setDiscount(getDiscountForBxGy(productIdGet, getQuantity, products));
				data.add(temp);
			}
			
		}
	}

	public Double getDiscountForBxGy(Set<Long> productIdGet, int getQuantity, List<ProductEntity> products) {
		return products.stream().filter(product -> productIdGet.contains(product.getProductId()))
				.mapToDouble(ProductEntity::getPrice).sorted().limit(getQuantity).sum();
	}
	public boolean hasCartRequiredProduct(Set<Long> productIds,Set<Long> compareIds, int target) {
		long count = compareIds.stream().filter(productIds::contains).limit(target).count();
		if(count>=target) {
			return true;
		}
		return false;
	}
	
	public void getProductWiseCoupon(List<ApplicableCouponModel> data,List<ProductModel> product) {
		List<Long> productIds = product.stream().map(a->a.getProductId()).collect(Collectors.toList());
		List<ProductEntity> productEntity = productRepo.findAll();
		
		
		List<MstCouponEntity> coupon = mstCouponRepo.findApplicableCouponByProductId(productIds).orElse(new ArrayList<>());
		for(MstCouponEntity ent : coupon) {
			ApplicableCouponModel temp = new ApplicableCouponModel();
			int productQuantity = product.stream()
					.filter(a->Objects.equals(ent.getProductWiseDetailEntity().getProductId(), a.getProductId())).findFirst().orElse(null)
					.getQuantity();
			Double productPrice = productEntity.stream()
					.filter(a->Objects.equals(ent.getProductWiseDetailEntity().getProductId(), a.getProductId())).findFirst().orElse(null)
					.getPrice();
			Double total = productQuantity * productPrice;
			Double discount = (total * ent.getProductWiseDetailEntity().getDiscountPercentage()) / 100;
			temp.setDiscount(discount);
			temp.setId(ent.getId());
			temp.setKey(ent.getProductWiseDetailEntity().getId());
			temp.setType(ent.getType());
			data.add(temp);
		}
	}
	
	public void getCartWiseCoupon(List<ApplicableCouponModel> data,List<ProductModel> product) {
		List<ProductEntity> productEntity = productRepo.findAll();
		
		Double cartAmount = getCartValue(product, productEntity);

		log.info("The total carValue is as "+cartAmount);
		
		List<MstCouponEntity> coupon = mstCouponRepo.findApplicableOnCartValue(cartAmount).orElse(new ArrayList<>());
		log.info("The size of the coupon list for cart wise is "+coupon.size());
		for(MstCouponEntity ent : coupon) {
			ApplicableCouponModel temp = new ApplicableCouponModel();
			Double discount = (cartAmount * ent.getCartWiseDetailEntity().getDiscountPercentage()) / 100;
			temp.setDiscount(discount);
			temp.setId(ent.getId());
			temp.setKey(ent.getCartWiseDetailEntity().getId());
			temp.setType(ent.getType());
			data.add(temp);
		}
	}

	private Double getCartValue(List<ProductModel> product, List<ProductEntity> productEntity) {
		Map<Long, Double> priceLookup = productEntity.stream()
				.collect(Collectors.toMap(ProductEntity::getProductId, ProductEntity::getPrice, (p1, p2) -> p1));

		Double cartAmount = product.stream().mapToDouble(item -> {
			Double price = priceLookup.getOrDefault(item.getProductId(), 0.0);
			return price * item.getQuantity();
		}).sum();
		return cartAmount;
	}

	public UpdatedCartReturnModel applyCoupon(Long id, List<ProductModel> product) {
		UpdatedCartReturnModel data = new UpdatedCartReturnModel();
		data.setProductModel(product);
		
		MstCouponEntity coupon = mstCouponRepo.findById(id).orElse(null);
		if(coupon.getType().equals(GlobalConstants.CART_WISE)) {
			applyCartWiseCoupon(data,coupon);
		}
		if(coupon.getType().equals(GlobalConstants.PRODUCT_WISE)) {
			applyProductWiseCoupon(data,coupon);
		}
		if(coupon.getType().equals(GlobalConstants.BXGY)) {
			applyBxGyCoupon(data,coupon);
		}
		
		return data;
	}

	private void applyProductWiseCoupon(UpdatedCartReturnModel data, MstCouponEntity coupon) {
		List<ProductEntity> productEntity = productRepo.findAll();
		
		Double cartValue = getCartValue(data.getProductModel(), productEntity);
		
		int quantity = data.getProductModel().stream()
				.filter(a->Objects.equals(a.getProductId(), coupon.getProductWiseDetailEntity().getProductId())).findAny().orElse(null).getQuantity();
		
		Double price = productEntity.stream()
				.filter(a->Objects.equals(a.getProductId(), coupon.getProductWiseDetailEntity().getProductId())).findAny().orElse(null).getPrice();
		Double discount = ((quantity * price) * coupon.getProductWiseDetailEntity().getDiscountPercentage()) / 100;
		
		data.getProductModel().stream()
		.filter(a->Objects.equals(a.getProductId(), coupon.getProductWiseDetailEntity().getProductId()))
		.findAny().orElse(null).setTotalDiscount(discount);
		
		data.setTotalPrice(cartValue);
		data.setTotalDiscount(discount);
		data.setFinalPrice(cartValue - discount);
		
	}
	private void applyBxGyCoupon(UpdatedCartReturnModel data, MstCouponEntity coupon) {
		List<ProductEntity> productEntity = productRepo.findAll();
		Double cartValue = getCartValue(data.getProductModel(), productEntity);
		
		
	}

	private void applyCartWiseCoupon(UpdatedCartReturnModel data, MstCouponEntity coupon) {
		List<ProductEntity> productEntity = productRepo.findAll();
		Double cartValue = getCartValue(data.getProductModel(), productEntity);
		
		Double totalDiscount = 0.00;
		if(cartValue>=coupon.getCartWiseDetailEntity().getThresholdAmount()) {
			totalDiscount = (cartValue * coupon.getCartWiseDetailEntity().getDiscountPercentage()) / 100;
		}
		
		data.setTotalPrice(cartValue);
		data.setTotalDiscount(totalDiscount);
		data.setFinalPrice(cartValue - totalDiscount);
		
	}

	
}
