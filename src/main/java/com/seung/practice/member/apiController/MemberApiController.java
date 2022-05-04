package com.seung.practice.member.apiController;

import com.seung.practice.common.token.JwtTokenProvider;
import com.seung.practice.member.application.internal.commandservice.AddMemberCommandService;
import com.seung.practice.member.application.internal.commandservice.LoginMemberCommandService;
import com.seung.practice.member.controller.dto.MemberFormDto;
import com.seung.practice.member.controller.dto.mapper.AddMemberMapper;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.domain.model.entites.UserRoles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static com.seung.practice.member.controller.constants.MemberWebUrl.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	// 각 로직 별 Service 의존관계 주입
	private final AddMemberCommandService addMemberCommandService;
	private final LoginMemberCommandService loginMemberCommandService;

	// birth, password 때문에 사용
	private final AddMemberMapper addMemberMapper;
	private final PasswordEncoder pwEnc;

	// Token 때문에 사용
	private final JwtTokenProvider jwtTokenProvider;


	// 회원가입 : add
	@PostMapping(ADD_MEMBER)
	public ResponseEntity<Member> create(
			@Valid @ModelAttribute("form") MemberFormDto form) {

		//TODO: error 처리는 exception handler 사용 (구글링)

		// 비밀번호 암호화해서 저장, 이메일 유효성 검증
		AddMemberCommand command = addMemberMapper.dtoToCommand(form, pwEnc);
		// 가입 실행
		Member member = addMemberCommandService.addMember(command);

		return new ResponseEntity<>(
				member,
				getSuccessHeaders(),
				HttpStatus.OK);
	}

	// 로그인 : login
	@PostMapping(LOGIN_MEMBER)
	public ResponseEntity<Member> login(
			@RequestParam("memberId") String memberId,
			@RequestParam("password") String password) {

		// TODO: error 처리는 exception handler 사용 (구글링)

		// 해당 회원 아이디 값으로 Member 가져오기
		Optional<Member> member = loginMemberCommandService.loginMember(memberId);

		// 암호화 된 비번이 입력한 비번과 동일한지 확인 ( pwEnc.matches() 사용 )
		if (!pwEnc.matches(password, pwEnc.encode(password))) {
			// TODO : 아이디 비번 불일치 -> 예외 처리 (?)
			throw new IllegalStateException("잘못된 비밀번호 입니다.");
		}
		// 로그인 시 토큰 생성
		jwtTokenProvider.createToken(member.get().getMemberId(), member.get().getRoles());

		return new ResponseEntity<Member>(
				getSuccessHeaders(),
				HttpStatus.OK);
	}


	// 회원 수정 : modify
	@PostMapping(MODIFY_MEMBER)
	public ResponseEntity<Member> modify() {

		return new ResponseEntity<Member>(
				getSuccessHeaders(),
				HttpStatus.OK);
	}



	// 회원 탈퇴 : delete
	@PostMapping(DELETE_MEMBER)
	public ResponseEntity<Member> delete() {

		return new ResponseEntity<Member>(
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