package club.banyuan.banyuanmall.product.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/5/22 9:45 上午
 */
@Configuration
@EnableTransactionManagement  //开启使用事务功能
@MapperScan("club.banyuan.banyuanmall.product.dao")
public class MybatisConfig {

  //引入分页插件
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
    // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
    paginationInterceptor.setOverflow(true);
    // 设置最大单页限制数量，默认 500 条，-1 不受限制
    paginationInterceptor.setLimit(1000);
    return paginationInterceptor;
  }

}
