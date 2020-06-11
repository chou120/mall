package club.banyuan.banyuanmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 品牌
 *
 * @author sanye
 * @email 1208160221@qq.com
 * @date 2020-05-08 09:08:44
 */
//为什么lombok注解没反应
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 品牌id
   */
  @TableId
  private Long brandId;
  /**
   * 品牌名
   */
  @NotBlank
  private String name;
  /**
   * 品牌logo地址
   */
  @NotBlank(message = "品牌不能为空")
  private String logo;
  /**
   * 介绍
   */
  private String descript;
  /**
   * 显示状态[0-不显示；1-显示]
   */
  private Integer showStatus;
  /**
   * 检索首字母
   */
  private String firstLetter;
  /**
   * 排序
   */
  private Integer sort;

}
