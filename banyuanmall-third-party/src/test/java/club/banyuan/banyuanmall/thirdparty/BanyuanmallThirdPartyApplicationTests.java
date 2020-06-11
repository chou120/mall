package club.banyuan.banyuanmall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import java.io.File;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BanyuanmallThirdPartyApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	OSSClient ossClient;

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
		PutObjectRequest putObjectRequest = new PutObjectRequest("banyuanmall", "1.jpg",
				new File("/Users/sanye/img/1.jpg"));

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



}
