package club.banyuan.banyuanmall.product.controller;

import club.banyuan.banyuanmall.common.utils.R;
import club.banyuan.banyuanmall.product.entity.CategoryEntity;
import club.banyuan.banyuanmall.product.service.CategoryService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 商品三级分类
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  /**
   * 商品服务 查出所有的分类以及子分类数据,以树形结构组合拼接起来
   */
  @RequestMapping("/list/tree")
  //@RequiresPermissions("product:category:list")
  public R list() {
    List<CategoryEntity> list = categoryService.listWithTree();

    return R.ok().put("data", list);//最终得到的结果是json格式的
  }


  /**
   * 信息xx
   */
  @RequestMapping("/info/{catId}")
  //@RequiresPermissions("product:category:info")
  public R info(@PathVariable("catId") Long catId) {
    CategoryEntity category = categoryService.getById(catId);

    return R.ok().put("category", category);
  }

  /**
   * 保存
   */
  @RequestMapping("/save")
  //@RequiresPermissions("product:category:save")
  public R save(@RequestBody CategoryEntity category) {
    categoryService.save(category);

    return R.ok();
  }

  @RequestMapping("/update/sort")
  //@RequiresPermissions("product:category:update")
  public R updateSort(@RequestBody CategoryEntity[] category){
    categoryService.updateBatchById(Arrays.asList(category));
    return R.ok();
  }


  /**
   * 修改
   */
  @RequestMapping("/update")
  //@RequiresPermissions("product:category:update")
  public R update(@RequestBody CategoryEntity category) {
    //可以进行级联更新了
    categoryService.updateCasecade(category);

    return R.ok();
  }

  /**
   * 删除
   *
   * @RequestBody:获取请求体,必须发送post请求 也只有post请求才有请求体 springmvc自动将请求体的数据(json),转为对象
   */
  @RequestMapping("/delete")
  //@RequiresPermissions("product:category:delete")
  public R delete(@RequestBody Long[] catIds) {
    //我们在做个删除的时候一定要考虑到如果当前要删除的编号被其他业务使用了,那就不能删除了 所以这个自动生成的办法没有效果了 重写一
    //
    categoryService.removeByIds(Arrays.asList(catIds));

    categoryService.removeMenuByIds(Arrays.asList(catIds));

    return R.ok();
  }

}
