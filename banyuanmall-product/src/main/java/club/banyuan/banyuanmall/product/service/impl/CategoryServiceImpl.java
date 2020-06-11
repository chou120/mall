package club.banyuan.banyuanmall.product.service.impl;

import club.banyuan.banyuanmall.product.service.CategoryBrandRelationService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.common.utils.Query;

import club.banyuan.banyuanmall.product.dao.CategoryDao;
import club.banyuan.banyuanmall.product.entity.CategoryEntity;
import club.banyuan.banyuanmall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        //1.查出所有分类
        //使用不适用 categoryDao  直接使用泛型里面的
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);

        //2.组装成父子树形结构
        /**
         * 2.1 找到所有的一级分类
         *
         */
        List<CategoryEntity> level1Menus = categoryEntities.stream()
            .filter(
                categoryEntity ->
            categoryEntity.getParentCid() == 0)
            .map(menu-> {
                menu.setChildren(getChildrens(menu, categoryEntities));
                return menu;
            }).sorted(
                (menu1,menu2)-> {
                    return  (menu1.getSort()==null?0:menu1.getSort())
                        -(menu2.getSort()==null?0:menu2.getSort());
                }).collect(Collectors.toList());

        return level1Menus;
    }

    //自己写一个删除的方法
    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO  1.检查当前删除的菜单,是否被别的地方引用

        //使用逻辑删除 不能使用物理删除  物理删除之后就真的删除了
        baseMapper.deleteBatchIds(asList);


    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long>  paths= new ArrayList<>();

        List<Long> parentPath = findParentPath(catelogId, paths);
        Collections.reverse(parentPath);

        return parentPath.toArray(new Long[parentPath.size()]);
    }



    //级联更新所有的关联数据
    @Override
    public void updateCasecade(CategoryEntity category) {
        //首先更新自己
        this.updateById(category);

        categoryBrandRelationService.updateCategory(category.getCatId(),category.getName());


    }

    //装进去可能是逆序装进去的   225  25  2  但是输出咱们是顺序输出的  所有 要进行转换Collections.reverse(parentPath);
    public List<Long> findParentPath(Long catelogId,List<Long> paths){
        CategoryEntity byId = this.getById(catelogId);
        if(byId.getParentCid()!=0){
            findParentPath(byId.getParentCid(),paths);
        }
        return  paths;
    }


    //写一个方法 用来获取当前菜单的子菜单
    private  List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all){

        //判断当前菜单是否是所有菜单的子菜单
        //因为子菜单可能还会有子菜单
        List<CategoryEntity> children = all.stream().filter(categoryEntity ->
            categoryEntity.getParentCid() == root.getCatId()
        ).map(categoryEntity -> {
            //找到当前菜单的子菜单
            categoryEntity.setChildren(getChildrens(categoryEntity, all));
            return categoryEntity;
        }).sorted(
            //找到子菜单之后进行排序
            (menu1,menu2)->{
                return  (menu1.getSort()==null?0:menu1.getSort())
                    -(menu2.getSort()==null?0:menu2.getSort());
            }
        ).collect(
            //根据上面的过滤的条件,对子菜单信息进行收集
            Collectors.toList());

        return  children;
    }
}