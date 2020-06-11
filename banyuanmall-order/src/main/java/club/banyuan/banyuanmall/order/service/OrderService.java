package club.banyuan.banyuanmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 16:55:57
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

