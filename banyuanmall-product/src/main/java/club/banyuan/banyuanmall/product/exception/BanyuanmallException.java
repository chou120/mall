package club.banyuan.banyuanmall.product.exception;

import club.banyuan.banyuanmall.common.utils.R;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author sanye
 * @version 1.0
 * @date 2020/5/20 2:14 下午
 */

//使用日志记录下
@Slf4j
//异常处理类
@RestControllerAdvice(basePackages = "club.banyuan.banyuanmall.product.controller")
public class BanyuanmallException {

  //处理所有的controller出现的异常
  //处理异常的种类
  @ExceptionHandler(value = Exception.class)
  public R handleVaildException(MethodArgumentNotValidException e){
    log.error("数据校验出现问题,异常类型,{}",e.getMessage(),e.getClass());
    BindingResult  result=e.getBindingResult();
    Map<String,String>  errorMap=new HashMap<>();
    result.getFieldErrors().forEach(fieldError -> {
      errorMap.put(fieldError.getField(),fieldError.getDefaultMessage());
    });
    return R.error("400,数据校验有问题").put("data",errorMap);
  }


}
