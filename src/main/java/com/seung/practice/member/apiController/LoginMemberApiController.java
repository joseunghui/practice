package com.seung.practice.member.apiController;

import com.seung.practice.common.exceptions.ErrorCode;
import com.seung.practice.common.exceptions.MemberException;
import com.seung.practice.common.token.JwtTokenProvider;
import com.seung.practice.member.application.internal.commandservice.LoginMemberCommandService;
import com.seung.practice.member.application.snsLogin.kakao.KakaoApiService;
import com.seung.practice.member.domain.model.aggregates.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.seung.practice.member.controller.constants.KaKaoApiUrl.KAKAO;
import static com.seung.practice.member.controller.constants.KaKaoApiUrl.KAKAO_LOGIN;
import static com.seung.practice.member.controller.constants.MemberWebUrl.LOGIN_MEMBER;

@RestController
@RequiredArgsConstructor
public class LoginMemberApiController {

    // 각 로직 별 Service 의존관계 주입
    private final LoginMemberCommandService loginMemberCommandService;
    private final KakaoApiService kakaoApiService;

    private final PasswordEncoder pwEnc;

    // Token 때문에 사용
    private final JwtTokenProvider jwtTokenProvider;

    // 로그인 : login
    @PostMapping(LOGIN_MEMBER)
    public ResponseEntity<String> login(
            // parameter 값으로 아이디, 패스워드를 가져오기? 아니면 Model 로??
            @RequestParam("memberId") String memberId,
            @RequestParam("password") String password) {

        // 해당 회원 아이디 값으로 Member 가져오기
        Optional<Member> member = loginMemberCommandService.loginMember(memberId);

        // 암호화 된 비번이 입력한 비번과 동일한지 확인 ( pwEnc.matches() 사용 )
        if (!pwEnc.matches(password, member.get().getPassword())) {
			throw new MemberException(ErrorCode.PASSWORD_NOT_MATCH);
		}
		return new ResponseEntity<>(
				jwtTokenProvider.createToken(member.get().getMemberId(), member.get().getRoles()),
				getSuccessHeaders(),
				HttpStatus.OK);

    }

    // sns-login : kakao
	/**
	 * url : https://kauth.kakao.com/oauth/authorize
	 * 보낼 파라미터 : client_id, redirect_uri, response_type( = "code")
	 */
	@GetMapping("/oauth")
	public ResponseEntity<HashMap> kakaoLogin(@RequestParam String code) {
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
