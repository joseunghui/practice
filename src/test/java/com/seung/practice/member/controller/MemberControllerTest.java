package com.seung.practice.member.controller;

import com.seung.practice.member.application.internal.commandservice.AddMemberCommandService;
import com.seung.practice.member.controller.dto.AddMemberFormDto;
import com.seung.practice.member.controller.dto.mapper.AddMemberMapper;
import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.testutil.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.seung.practice.member.controller.constants.MemberWebUrl.ADD_MEMBER;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class MemberControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    MemberController memberController;

    @Mock
    AddMemberCommandService addMemberCommandService;

    @Mock
    AddMemberMapper addMemberMapper;
    @Mock PasswordEncoder pwEnc;


    @BeforeEach
    public void setupMockMbc(){
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(memberController)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
//                .setControllerAdvice(ApiExceptionHandler.class)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("회원을 성공적으로 생성 한다.")
    void createMember() throws Exception {
        //given
        AddMemberFormDto form = TestData.mockAddMemberFormDto;
        String mockFormData = TestData.MockMemberFormString;
        AddMemberCommand cmd = TestData.mockAddMemberCommand;
        Member member = TestData.mockMember;

        //when
        given(addMemberMapper.dtoToCommand(form, pwEnc)).willReturn(null);
        given(addMemberCommandService.addMember(cmd)).willReturn(member);

        //then
        mockMvc.perform(
                        MockMvcRequestBuilders.post(ADD_MEMBER)
                                .contentType(APPLICATION_FORM_URLENCODED_VALUE)
                                .content(mockFormData)
                )
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", is("redirect:/")))
                ;
    }
}