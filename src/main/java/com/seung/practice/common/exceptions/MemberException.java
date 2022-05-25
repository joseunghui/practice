package com.seung.practice.common.exceptions;

import lombok.Getter;

/**
 * 전역 오류 처리 : @ControllerAdvice -> @ResponseBody 를 포함(객체를 리턴하는 것도 가능)
 * @Controller 기반 오류 처리 : @ExceptionHandler
 */
@Getter
public class MemberException extends RuntimeException {
	private ErrorCode errorCode;

	// member
	public MemberException(ErrorCode code) {
		super(code.getMessage());
		this.errorCode = code;
	}

}
