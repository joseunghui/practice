package com.seung.practice.member.apiController;

import com.seung.practice.member.application.snsLogin.kakao.KakaoApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.seung.practice.member.controller.constants.KaKaoApiUrl.KAKAO;

@RestController
@RequiredArgsConstructor
public class KakaoController {

	// 각 로직 별 Service 의존관계 주입
	private final KakaoApiService kakaoApiService;


	// sns-login : kakao
	/**
	 *	url : https://kauth.kakao.com/oauth/authorize
	 *	보낼 파라미터 : code(String code, "code")
	 */
	@RequestMapping(value = KAKAO, method = RequestMethod.GET)
	public ResponseEntity<HashMap> kakaoLogin(@RequestParam(value = "code", required = false) String code) {

		String access_Token = kakaoApiService.getAccessToken(code);
		HashMap<String, Object> userInfo = kakaoApiService.getUserInfo(access_Token);

		return new ResponseEntity<>(
				userInfo,
				getSuccessHeaders(),
				HttpStatus.OK);
	}


	// headers 이용 에러 설정
	protected HttpHeaders getSuccessHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("resultCode", "0000");
		headers.set("resultMessage", "정상처리 하였습니다.");
		return headers;
	}


}
