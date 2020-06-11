package club.banyuan.banyuanmall.order.dao;

import club.banyuan.banyuanmall.order.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 16:55:58
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseMapper<OrderOperateHistoryEntity> {
	
}
