package club.banyuan.banyuanmall.product.dao;

import club.banyuan.banyuanmall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 属性&属性分组关联
 * 
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

	void  deleteBatchIds(@Param("entities") List<AttrAttrgroupRelationEntity> entities);

}
