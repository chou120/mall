package club.banyuan.banyuanmall.coupon;

import club.banyuan.banyuanmall.coupon.entity.CouponEntity;
import club.banyuan.banyuanmall.coupon.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BanyuanmallCouponApplicationTests {

  @Autowired
  CouponService couponService;
  @Test
  void contextLoads() {
//    CouponEntity couponEntity = new CouponEntity();
//
//    couponService.save(couponEntity);
  }

}
