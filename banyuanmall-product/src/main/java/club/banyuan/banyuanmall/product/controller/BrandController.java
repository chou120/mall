package club.banyuan.banyuanmall.product.controller;

import club.banyuan.banyuanmall.common.utils.PageUtils;
import club.banyuan.banyuanmall.common.utils.R;
import club.banyuan.banyuanmall.product.entity.BrandEntity;
import club.banyuan.banyuanmall.product.service.BrandService;
import java.util.Arrays;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 品牌
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {

  @Autowired
  private BrandService brandService;

  /**
   * 列表
   */
  @RequestMapping("/list")
  //@RequiresPermissions("product:brand:list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = brandService.queryPage(params);

    return R.ok().put("page", page);
  }


  /**
   * 信息
   */
  @RequestMapping("/info/{brandId}")
  //@RequiresPermissions("product:brand:info")
  public R info(@PathVariable("brandId") Long brandId) {
    BrandEntity brand = brandService.getById(brandId);

    return R.ok().put("brand", brand);
  }

  /**
   * 保存
   */
  @RequestMapping("/save")  //添加校验注解功能
  //@RequiresPermissions("product:brand:save")
  public R save( @RequestBody BrandEntity brand) {

    brandService.save(brand);

    return R.ok();
  }

  /**
   * 修改
   */
  @RequestMapping("/update/status")
  //@RequiresPermissions("product:brand:update")
  public R update( @RequestBody BrandEntity brand) {
    brandService.updateById(brand);

    return R.ok();
  }

  @RequestMapping("/update")
  //@RequiresPermissions("product:brand:update")
  public R updateAll(@RequestBody BrandEntity brand) {

    //一般情况下,更新某张表的同时相关的表也会进行更新,不能单独只更新一张表
    brandService.updateDetail(brand);

    return R.ok();
  }

  /**
   * 删除
   */
  @RequestMapping("/delete")
  //@RequiresPermissions("product:brand:delete")
  public R delete(@RequestBody Long[] brandIds) {
    brandService.removeByIds(Arrays.asList(brandIds));

    return R.ok();
  }

}
