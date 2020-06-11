package club.banyuan.banyuanmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.coupon.entity.SeckillSkuNoticeEntity;

import java.util.Map;

/**
 * 秒杀商品通知订阅
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 16:51:04
 */
public interface SeckillSkuNoticeService extends IService<SeckillSkuNoticeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

