package club.banyuan.banyuanmall.product.service;

import club.banyuan.banyuanmall.product.vo.AttrGroupRelationVo;
import club.banyuan.banyuanmall.product.vo.AttrRespVo;
import club.banyuan.banyuanmall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.product.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

  void saveAttr(AttrVo attr);

  PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type);

  AttrRespVo getAttrId(Long attrId);

  List<AttrEntity> getRelationAttr(Long attrGroupId);

  abstract void deleteRelation(AttrGroupRelationVo[] vos);
}

