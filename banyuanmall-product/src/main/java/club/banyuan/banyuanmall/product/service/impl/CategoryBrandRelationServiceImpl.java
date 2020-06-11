package club.banyuan.banyuanmall.product.service.impl;

import club.banyuan.banyuanmall.product.dao.BrandDao;
import club.banyuan.banyuanmall.product.dao.CategoryDao;
import club.banyuan.banyuanmall.product.entity.BrandEntity;
import club.banyuan.banyuanmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.common.utils.Query;

import club.banyuan.banyuanmall.product.dao.CategoryBrandRelationDao;
import club.banyuan.banyuanmall.product.entity.CategoryBrandRelationEntity;
import club.banyuan.banyuanmall.product.service.CategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    BrandDao brandDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    //保存数据详情
    @Override
    public void saveDatail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        /** 1.查询品牌和分类的名字  所以要用到品牌和分类的dao
         */
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);

        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());

        this.save(categoryBrandRelation);

    }

    @Override
    public void updateBrand(Long brandId, String name) {

        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandName(name);
        categoryBrandRelationEntity.setBrandId(brandId);

        System.out.println("---"+categoryBrandRelationEntity.toString());

        this.update(categoryBrandRelationEntity,
            new UpdateWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));

        /**
         * 如果只加一个brand_id 作为条件 那么在更改品牌信息的时候  比如说  更改一个  品牌名  改为2
         * 在做关联更改的时候就没办法一起修改了,所以还需要在上述代码上面加上另一个条件  name
         */


    }

    @Override
    public void updateCategory(Long catId, String name) {
        this.baseMapper.updateCategory(catId,name);

    }

}