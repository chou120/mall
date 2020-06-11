package club.banyuan.banyuanmall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


/**
 * @author sanye
 * @version 1.0
 * @date 2020/5/15 9:21 上午
 */
//配置网关跨域  因为所有的请求都是通过网关进行路由到各个服务  所以我们统一在网关服务里面进行配置就行了
@Configuration
public class CorsConfig {

  @Bean
  public CorsWebFilter corsWebFilter(){
    UrlBasedCorsConfigurationSource source
        = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();
    //1.配置跨域
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.addAllowedOrigin("*");
    configuration.setAllowCredentials(true);//是否携带Cookie进行跨域
    source.registerCorsConfiguration("/**", configuration);
    return  new CorsWebFilter(source);
  }
}


class   A{

  public static void main(String[] args) {
    new Thread(){
      @Override
      public void run() {
        method();
      }
    };
  }
  static  void  method(){

  }
}