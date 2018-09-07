package xyz.amazingxu.auShop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.amazingxu.core.utils.WebResults;
import xyz.amazingxu.core.utils.WebUtils;

/**
 * 拦截器
 * @author xuwang <121894598@qq.com>
 * @date 2018/6/4 15:31
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    WebResults handleException(Exception ex){
        return WebUtils.error(-1,ex.getMessage());
    }

}