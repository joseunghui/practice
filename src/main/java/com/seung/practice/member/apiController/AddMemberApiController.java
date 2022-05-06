package com.seung.practice.member.apiController;

import com.seung.practice.member.application.internal.commandservice.AddMemberCommandService;
import com.seung.practice.member.controller.dto.AddMemberFormDto;
import com.seung.practice.member.controller.dto.mapper.AddMemberMapper;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.seung.practice.member.controller.constants.MemberWebUrl.ADD_MEMBER;

@RestController
@RequiredArgsConstructor
public class AddMemberApiController {

    // 각 로직 별 Service 의존관계 주입
    private final AddMemberCommandService addMemberCommandService;

    // birth, password 때문에 사용
    private final AddMemberMapper addMemberMapper;
    private final PasswordEncoder pwEnc;

    // 회원가입 : add
    @PostMapping(ADD_MEMBER)
    public ResponseEntity create(
            @Valid @ModelAttribute("form") AddMemberFormDto form) {

        //TODO: error 처리는 exception handler 사용 (구글링)

        // 비밀번호 암호화해서 저장, 생년월일 타입 변경
        AddMemberCommand command = addMemberMapper.dtoToCommand(form, pwEnc);
        // 가입 실행
        Member member = addMemberCommandService.addMember(command);

        return new ResponseEntity<>(
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
