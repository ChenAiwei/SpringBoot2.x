package com.boot.common.handler;

import com.boot.common.model.AppJSONResult;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * @author aiwei
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public AppJSONResult defaultErrorHandler(HttpServletRequest request , HttpServletResponse response, Exception e) {
		AppJSONResult result = new AppJSONResult();
		result.setMsg(e.getMessage());
		result.setStatus(-1);
		return result;
	}
}
