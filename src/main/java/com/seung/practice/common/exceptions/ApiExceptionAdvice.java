package com.seung.practice.common.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionAdvice {

	@ExceptionHandler(MemberException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, final MemberException e) {
		return ResponseEntity
				.status(e.getErrorCode().getStatus())
				.body(ErrorResponse.builder()
						.code(e.getErrorCode().getCode())
						.message(e.getErrorCode().getMessage())
						.build());
	}

}
