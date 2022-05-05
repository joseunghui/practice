package com.seung.practice.member.controller.constants;

public class KaKaoApiUrl {
	// kakao api 로그인 요청
	public static final String KAKAO = "/api/kakao/members/login";

	/**
	 * 카카오 로그인 페이지
	 * https://kauth.kakao.com/oauth/authorize
	 */
	public static final String KAKAO_LOGIN = "https://kauth.kakao.com/oauth/authorize";

	/**
	 * 카카오 토큰 발급 URL
	 * https://kauth.kakao.com/oauth/token
	 */
	public static final String KAKAO_API_GET_TOKEN = "https://kauth.kakao.com/oauth/token";

	/**
	 * 카카오 엑세스 토큰 검증 URL
	 * https://kapi.kakao.com/user/access_token_info
	 */
	public static final String KAPI_CHECK_ACCESS_TOKEN_URL = "https://kapi.kakao.com/user/access_token_info";

	/**
	 * 카카오 회원 정보 조회 URL
	 * https://kapi.kakao.com/user/me
	 */
	public static final String KAPI_USER_INFO_URL = "https://kapi.kakao.com/user/me";

}
