package com.swpu.constructionsitesafety.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 默认全局异常处理
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResultData<String> exception(Exception e) {
		logger.error("全局异常信息 ex={}", e.getMessage(), e);
		return ResultData.fail(ReturnCode.RC500.getCode(), e.getMessage());
	}
}
