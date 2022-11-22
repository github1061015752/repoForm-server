package com.xjs.aop;


import com.xjs.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Spring专门为解决异常问题,开发了针对Controller层的注解
//注解的核心就是 Spring-AOP
@RestControllerAdvice
public class SystemException {

    /**
     * 拦截什么异常: 运行时异常
     * 返回值: SysResult对象 --201
     * @ExceptionHandler: 切入点表达式
     */
    @ExceptionHandler(RuntimeException.class)
    public SysResult fail(Exception e){
        e.printStackTrace();
        return SysResult.fail();
    }
}
