package com.seung.practice.member.apiController;

import com.seung.practice.common.exceptions.ErrorCode;
import com.seung.practice.common.exceptions.MemberException;
import com.seung.practice.member.application.internal.commandservice.DeleteMemberCommandService;
import com.seung.practice.member.domain.model.aggregates.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.seung.practice.member.controller.constants.MemberWebUrl.DELETE_MEMBER;

@RestController
@RequiredArgsConstructor
public class DeleteMemberApiController {

    // 각 로직 별 Service 의존관계 주입
    private final DeleteMemberCommandService deleteMemberCommandService;

    // birth, password 때문에 사용
    private final PasswordEncoder pwEnc;


    // 회원 탈퇴 : delete
    @PostMapping(DELETE_MEMBER)
    public ResponseEntity<String> delete(
            // parameter 값으로 아이디, 패스워드를 가져오기? 아니면 Model 로??
            @RequestParam("memberId") String memberId,
            @RequestParam("password") String password) {

        // 해당 회원 아이디 값으로 Member 가져오기
        // loginMemberCommandService.loginMember 메소드에 동일한 기능이 있는데 그걸로 사용해도...?
        Optional<Member> member = deleteMemberCommandService.getMember(memberId);

        // 암호화 된 비번이 입력한 비번과 동일한지 확인 ( pwEnc.matches() 사용 )
        if (!pwEnc.matches(password, member.get().getPassword())) {
			throw new MemberException(ErrorCode.PASSWORD_NOT_MATCH);
        } else {
			// 삭제 실행
			deleteMemberCommandService.deleteMember(member);

			return new ResponseEntity<>(
					"delete-Success",
					getSuccessHeaders(),
					HttpStatus.OK);
		}
    }

    // headers 이용 에러 설정
    protected HttpHeaders getSuccessHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("resultCode", "0000");
        headers.set("resultMessage", "정상처리 하였습니다.");
        return headers;
    }
}
