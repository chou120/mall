package club.banyuan.banyuanmall.product.service.impl;

import club.banyuan.banyuanmall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.common.utils.Query;

import club.banyuan.banyuanmall.product.dao.BrandDao;
import club.banyuan.banyuanmall.product.entity.BrandEntity;
import club.banyuan.banyuanmall.product.service.BrandService;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        //1.进行模糊查询
        String key=(String)params.get("key");
        //添加检索条件
        QueryWrapper<BrandEntity> objectQueryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(key)){
            objectQueryWrapper.eq("brand_id", key).or().like("name", key);
        }

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
            objectQueryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void updateDetail(BrandEntity brand) {

        //首先让自己表里面的数据更新一致
        this.updateById(brand);

        if(!StringUtils.isEmpty(brand.getName())){
            //同步更新其他关联表中的数据, 更新品牌信息
            categoryBrandRelationService.updateBrand(
                brand.getBrandId(),brand.getName()
            );
            //TODO    再更新其他关联

        }

    }

}