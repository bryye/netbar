package com.nuo.netbar;


import com.nuo.netbar.common.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public AjaxResult exceptionHander(Exception e) {
		return AjaxResult.failed(e.getCause().toString());
	}

}
