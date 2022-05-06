package com.seung.practice.member.apiController;

import com.seung.practice.common.token.JwtTokenProvider;
import com.seung.practice.member.application.internal.commandservice.AddMemberCommandService;
import com.seung.practice.member.application.internal.commandservice.DeleteMemberCommandService;
import com.seung.practice.member.application.internal.commandservice.LoginMemberCommandService;
import com.seung.practice.member.application.internal.commandservice.ModifyMemberCommandService;
import com.seung.practice.member.application.service.MemberService;
import com.seung.practice.member.controller.dto.AddMemberFormDto;
import com.seung.practice.member.controller.dto.ModifyMemberFormDto;
import com.seung.practice.member.controller.dto.mapper.AddMemberMapper;
import com.seung.practice.member.controller.dto.mapper.ModifyMemberMapper;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.domain.model.commands.ModifyMemberCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

import static com.seung.practice.member.controller.constants.MemberWebUrl.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

	// 각 로직 별 Service 의존관계 주입
	private final MemberService memberService;

	// 회원 조회(전체 목록 조회)
	@GetMapping("/api/members")
	public ResponseEntity<List<Member>> list() {

		// 전체 회원 조회
		List<Member> memberList = memberService.memberFindAll();

		return new ResponseEntity<>(
				memberList,
				getSuccessHeaders(),
				HttpStatus.OK);
	}

	// 회원 조회(아이디로 조회)
	@PutMapping("/api/members/{memberId}")
	public ResponseEntity<Member> findOne(@PathVariable("memberId") String memberId) {

		// 회원 아이디로 조회
		Member member = memberService.memberFindOne(memberId);

		return new ResponseEntity<>(
				member,
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