package com.shehab.couponmngmnt.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shehab.couponmngmnt.entity.MstCouponEntity;

@Repository
public interface IMstCouponRepo extends JpaRepository<MstCouponEntity, Long> {

	@Query(value = "select data from MstCouponEntity data join fetch data.cartWiseDetailEntity b where b.thresholdAmount<=:cartAmount")
	Optional<List<MstCouponEntity>> findApplicableOnCartValue(@Param("cartAmount") Double cartAmount);

	@Query(value = "select data from MstCouponEntity data join fetch data.productWiseDetailEntity b where b.productId in :productIds")
	Optional<List<MstCouponEntity>> findApplicableCouponByProductId(@Param("productIds") List<Long> productIds);

	@Query(value = "SELECT * FROM T_MST_COUPONS A \r\n"
			+ "LEFT JOIN T_BXGY_DETAILS B ON A.ID=B.COUPON_ID\r\n"
			+ "LEFT JOIN T_BXGY_PRODUCTS_ARRAY C ON B.ID = C.BXGY_DETAIL_ID\r\n"
			+ "WHERE A.TYPE='bxgy' and PRODUCT_ID IN (:productIds)",nativeQuery = true)
	Optional<List<MstCouponEntity>> findApplicableBxGyCouponInProductId(@Param("productIds") Set<Long> productIds);

}
