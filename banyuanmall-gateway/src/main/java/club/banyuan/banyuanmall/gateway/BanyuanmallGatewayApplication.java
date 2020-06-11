package club.banyuan.banyuanmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1、开启服务注册发现
 *  (配置nacos的注册中心地址)
 * 2、编写网关配置文件
 */
@EnableDiscoveryClient
/**
 * 启动的时候会报错:
 * Failed to configure a DataSource: 'url' attribute is not specified
 * 	and no embedded datasource could be configured.
 *
 * Reason: Failed to determine a suitable driver class
 * 如下解决方式
 */
//排除跟数据库有关的操作
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BanyuanmallGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanyuanmallGatewayApplication.class, args);
	}

}
