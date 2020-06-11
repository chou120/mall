package club.banyuan.banyuanmall.member.feign;

import club.banyuan.banyuanmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/5/12 7:57 下午
 */
//调用服务中心的哪个服务  直接调用服务中心的服务名就行了 远程调用
@FeignClient("banyuanmall-coupon")
public interface CouponFeignService {

  //那么具体调用哪个方法呢  这个路径要写全了    表示调用别的服务的这个方法
  @RequestMapping("/coupon/coupon/member/list")
  public R membercoupon();

}
