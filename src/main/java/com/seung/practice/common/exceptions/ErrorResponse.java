package com.seung.practice.common.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter @ToString
public class ErrorResponse {
	/**
	 * message : Error메시지
	 * code : 자체 정의한 ErrorCode
	 * detail : 추가 정보, 또는 getMessage 값
	 */
	private String message;
	private String code;
	private int status;

	@Builder
	public ErrorResponse(HttpStatus status, String code, String message) {
		this.code = code;
		this.message = message;
	}

}
