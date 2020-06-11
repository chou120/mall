package club.banyuan.banyuanmall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BanyuanmallWareApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanyuanmallWareApplication.class, args);
	}

}
