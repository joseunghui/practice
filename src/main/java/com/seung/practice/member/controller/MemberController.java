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

import static com.seung.practice.member.controller.constants.MemberWebUrl.ADD_MEMBER;


@Controller
@RequiredArgsConstructor
public class MemberController {

//	private final MemberService memberService;
	private final AddMemberCommandService addMemberCommandService;
//	private final MemberRepository memberRepository;

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
	@PostMapping(ADD_MEMBER)
	public ResponseEntity<String> create(@Valid @ModelAttribute("form") MemberFormDto form, BindingResult result, RedirectAttributes redirectAttributes) { // 검증을 위한 바인딩 추가
		// 아이디 중복체크
//		if (!memberRepository.findByMemberId(form.getMemberId()).isEmpty()) {
//			result.reject("existsMemberId", "사용 중인 아이디 입니다.");
//		}

		//TODO: error 처리는 exception handler 사용 (구글링)
//		// 검증에 실패하면 다시 가입화면으로
//		if (result.hasErrors()) {
//			return "members/createMember";
//		}
//
//		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
//
//		// member 객체에 입력한 정보 담기
//		Member member = new Member();
//
//		member.setMemberId(form.getMemberId());
//		member.setPassword(form.getPassword());
//		member.setName(form.getName());
//
//		member.setGender(form.getGender());
//		member.setEmail(form.getEmail());
//		member.setPhone(form.getPhone());
//		member.setBirth(form.getBirth());
//
//		member.setAddress(address);

		AddMemberCommand command = addMemberMapper.dtoToCommand(form, pwEnc);
		// 가입 실행
		Member member = addMemberCommandService.addMember(command);

		return new ResponseEntity<>(
				"redirect:/",
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


	protected HttpHeaders getSuccessHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("resultCode", "0000");
		headers.set("resultMessage", "정상처리 하였습니다.");
		return headers;
	}
}