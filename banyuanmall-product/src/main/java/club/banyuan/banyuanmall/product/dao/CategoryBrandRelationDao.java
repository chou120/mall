package club.banyuan.banyuanmall.product.dao;

import club.banyuan.banyuanmall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌分类关联
 * 
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

  //需要自己在配置文件中添加sql语句 添加注解是为了获取自定义参数值
  void updateCategory(@Param("catId") Long catId,@Param("name") String name);
}
