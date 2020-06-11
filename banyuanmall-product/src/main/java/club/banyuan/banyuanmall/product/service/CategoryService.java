package club.banyuan.banyuanmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
public interface CategoryService extends IService<CategoryEntity> {

  PageUtils queryPage(Map<String, Object> params);

  List<CategoryEntity> listWithTree();

  void removeMenuByIds(List<Long> asList);

  /**
   * 找到catelogId完整路径
   * [父路径/子路径]
    * @param catelogId
   * @return
   */
  Long[] findCatelogPath(Long catelogId);

  void updateCasecade(CategoryEntity category);
}

