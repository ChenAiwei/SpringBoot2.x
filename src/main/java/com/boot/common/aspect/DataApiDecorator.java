package com.boot.common.aspect;

import com.boot.common.model.AppJSONResult;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataApiDecorator {
	
	@Around("within(com.boot..controller..*) && @annotation(com.boot.common.annotation.DataAPI)")
	public Object decorate(ProceedingJoinPoint joinPoint) throws Throwable {
		AppJSONResult result = new AppJSONResult();
	/*	try {
			Object proceed = joinPoint.proceed();
			result.setStatus(200);
			result.setData(proceed);
		} catch (Exception e) {
			// TODO: handle exception
			result.setStatus(500);
			result.setMsg(e.getMessage());
		}*/
	    //异常统一由全局异常拦截处理，这里不在做处理
        Object proceed = joinPoint.proceed();
        result.setStatus(200);
        result.setData(proceed);
		return result;
	}
}
