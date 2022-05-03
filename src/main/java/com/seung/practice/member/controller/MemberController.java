package com.seung.practice.member.controller;

import com.seung.practice.member.application.internal.commandservice.AddMemberCommandService;
import com.seung.practice.member.controller.dto.MemberFormDto;
import com.seung.practice.member.controller.dto.mapper.AddMemberMapper;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.domain.model.aggregates.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.net.URI;

import static com.seung.practice.member.controller.constants.MemberWebUrl.ADD_MEMBER;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final AddMemberCommandService addMemberCommandService;
	private final AddMemberMapper addMemberMapper;
	private final PasswordEncoder pwEnc;

	// 기본 실행
	@GetMapping("/")
	public String home() {
		return "index";
	}

	// 회원가입 페이지로 이동
	@GetMapping("/members/create")
	public String createForm(Model model) {
		model.addAttribute("form", new Member());
		return "members/createMember";
	}

	// 회원가입 실행
	// @PostMapping(ADD_MEMBER)
	@PostMapping("/members/create")
	public ResponseEntity<String> create(
			@Valid @ModelAttribute("form") MemberFormDto form) { // 검증을 위한 바인딩 추가

		//TODO: error 처리는 exception handler 사용 (구글링)

		// 비밀번호 암호화해서 저장, 이메일 유효성 검증
		AddMemberCommand command = addMemberMapper.dtoToCommand(form, pwEnc);
		// 가입 실행
		Member member = addMemberCommandService.addMember(command);

		return new ResponseEntity<>(
				"redirect:",
				// getSuccessHeaders(),
				getSuccessHeaders(),
				HttpStatus.OK);
	}

	// 로그인 페이지로 이동
	@GetMapping("/members/login")
	public String loginForm() {
		return "members/loginForm";
	}

	// 로그인 실행
	@PostMapping("/members/login")
	public String login() {
		return "";
	}

	// headers 이용 에러 설정
	protected HttpHeaders getSuccessHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("resultCode", "0000");
		headers.set("resultMessage", "정상처리 하였습니다.");
		return headers;
	}
}