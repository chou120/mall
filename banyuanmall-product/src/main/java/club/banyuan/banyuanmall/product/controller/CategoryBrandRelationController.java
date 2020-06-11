package club.banyuan.banyuanmall.product.controller;

import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.common.utils.R;
import club.banyuan.banyuanmall.product.entity.CategoryBrandRelationEntity;
import club.banyuan.banyuanmall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 品牌分类关联
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {

  @Autowired
  private CategoryBrandRelationService categoryBrandRelationService;

  /**
   * 获取当前列表品牌的所有的分类
   */
  // @RequestMapping("/catelog/list")
  //@RequiresPermissions("product:categorybrandrelation:list")
  @GetMapping("/catelog/list")
  public R categoryList(@RequestParam("brandId") Long brandId) {
    List<CategoryBrandRelationEntity> data = categoryBrandRelationService.list(
        new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));

    return R.ok().put("data", data);
  }


  @RequestMapping("/list")
  //@RequiresPermissions("product:categorybrandrelation:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = categoryBrandRelationService.queryPage(params);

    return R.ok().put("page", page);
  }


  /**
   * 信息
   */
  @RequestMapping("/info/{id}")
  //@RequiresPermissions("product:categorybrandrelation:info")
  public R info(@PathVariable("id") Long id) {
    CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

    return R.ok().put("categoryBrandRelation", categoryBrandRelation);
  }

  /**
   * 保存
   */
  @RequestMapping("/save")
  public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {

    //在一般的大型的电商网站中,一般很少使用表与表之间的关联关系进行查询
    //宁愿多使用几次查询,也不要使用关联关系查询,会降低数据库性能
    //咱们修改一下保存的方法
    categoryBrandRelationService.saveDatail(categoryBrandRelation);
    return R.ok();
  }

  /**
   * 修改
   */
  @RequestMapping("/update")
  //@RequiresPermissions("product:categorybrandrelation:update")
  public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation) {
    categoryBrandRelationService.updateById(categoryBrandRelation);

    return R.ok();
  }

  /**
   * 删除
   */
  @RequestMapping("/delete")
  //@RequiresPermissions("product:categorybrandrelation:delete")
  public R delete(@RequestBody Long[] ids) {
    categoryBrandRelationService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

}
