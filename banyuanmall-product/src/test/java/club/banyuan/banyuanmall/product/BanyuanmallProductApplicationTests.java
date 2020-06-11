package club.banyuan.banyuanmall.product;

import club.banyuan.banyuanmall.product.entity.BrandEntity;
import club.banyuan.banyuanmall.product.entity.CategoryEntity;
import club.banyuan.banyuanmall.product.service.BrandService;
import club.banyuan.banyuanmall.product.service.CategoryService;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BanyuanmallProductApplicationTests {

  @Autowired
  BrandService brandService;

  @Autowired
  OSSClient  ossClient;

  @Autowired
  CategoryService categoryService;

  @Test
  public void testUpload() {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
//    String endpoint = "oss-cn-shanghai.aliyuncs.com";
//// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
//    String accessKeyId = "LTAI4GHAZexBck1Y2CUCwv1b";
//    String accessKeySecret = "2gVKwYYW9IyvDW8OijxxkejgMWfi0p";
//
//// 创建OSSClient实例。
//    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    /**
     * 使用阿里封装好了的oss测试   上面的 就不需要了
     *
      */
// 创建PutObjectRequest对象。
    PutObjectRequest putObjectRequest = new PutObjectRequest("banyuanmall", "4BF7C076-491E-4562-A9A1-0E5F9354469C.png",
        new File("/Users/sanye/img/4BF7C076-491E-4562-A9A1-0E5F9354469C.png"));

// 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
// metadata.setObjectAcl(CannedAccessControlList.Private);
// putObjectRequest.setMetadata(metadata);

// 上传文件。
    ossClient.putObject(putObjectRequest);

// 关闭OSSClient。
    ossClient.shutdown();
    System.out.println("上传完成...");
  }


  @Test
  void contextLoads() {

    BrandEntity brandEntity = new BrandEntity();
    brandEntity.setDescript("华为吊炸天");
    brandEntity.setName("华为");
    brandEntity.setSort(1);

    brandService.save(brandEntity);

    //比如说现在根据某个条件来查询  QueryWrapper
    List<BrandEntity> list = brandService
        .list(new QueryWrapper<BrandEntity>().eq("brand_id", 2L));
    list.forEach((item) ->
        System.out.println(item)
    );

  }
  @Test
  void  testRelation(){

    BrandEntity brandEntity = brandService.getById(4L);
    brandEntity.setName("小米5");

    brandService.updateDetail(brandEntity);
  }


  @Test
  void testUpdate(){
    CategoryEntity categoryEntity = new CategoryEntity();
    categoryEntity.setCatId(225L);
    categoryEntity.setName("shouji");
    categoryService.updateCasecade(categoryEntity);
  }



}
