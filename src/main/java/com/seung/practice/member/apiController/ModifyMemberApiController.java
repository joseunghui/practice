package com.seung.practice.member.apiController;

import com.seung.practice.member.application.internal.commandservice.ModifyMemberCommandService;
import com.seung.practice.member.controller.dto.ModifyMemberFormDto;
import com.seung.practice.member.controller.dto.mapper.ModifyMemberMapper;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.ModifyMemberCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.seung.practice.member.controller.constants.MemberWebUrl.MODIFY_MEMBER;

@RestController
@RequiredArgsConstructor
public class ModifyMemberApiController {

    // 각 로직 별 Service 의존관계 주입
    private final ModifyMemberCommandService modifyMemberCommandService;

    // birth, password 때문에 사용
    private final ModifyMemberMapper modifyMemberMapper;
    private final PasswordEncoder pwEnc;


    // 회원 수정 : modify
    @PostMapping(MODIFY_MEMBER)
    public ResponseEntity modify(
            @Valid @ModelAttribute("form") ModifyMemberFormDto form) {

        //TODO: error 처리는 exception handler 사용 (구글링)

        // 회원 정보 수정 전 우선확인 사항
        modifyMemberCommandService.getMember(form.getMemberId());

        // 기존 정보 : member
        // 변경할 정보 : form -> command
        ModifyMemberCommand command = modifyMemberMapper.dtoToCommand(form, pwEnc);

        // 변경 실행
        Member modifyMember = modifyMemberCommandService.modifyMember(command);

        return new ResponseEntity<>(
                modifyMember,
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
