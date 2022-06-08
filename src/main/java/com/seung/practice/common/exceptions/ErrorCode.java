package com.seung.practice.common.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public enum ErrorCode {
	// common
	INVALID_CODE(400, "C001", "Invalid Code"),
	RESOURCE_NOT_FOUND(204, "C002", "Resource not found"),
	EXPIRED_CODE(400, "C003", "Expired Code"),
	INTER_SERVER_ERROR(500,"C004" , "Inter Server Error"),

	// aws 관련 에러
	AWS_ERROR(400, "A001", "aws client error"),

	 // member API 관련 에러
	USER_EXISTS(6000, "6000", "Already User exists"),
	PASSWORD_NOT_MATCH(6001, "6001", "Password is not correct"),
	ID_UNCHANGEABLE(6002, "6002", "Unchangeable user Id"),
	USER_NOT_FOUND(6003, "6003", "User not found");

	private final int status;
	private final String code;
	private final String message;

	ErrorCode(int status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
