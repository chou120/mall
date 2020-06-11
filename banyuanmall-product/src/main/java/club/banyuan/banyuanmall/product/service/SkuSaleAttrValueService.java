package club.banyuan.banyuanmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.product.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku销售属性&值
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

