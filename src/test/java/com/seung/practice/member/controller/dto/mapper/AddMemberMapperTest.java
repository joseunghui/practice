package com.seung.practice.member.controller.dto.mapper;

import com.seung.practice.config.SecurityConfig;
import com.seung.practice.member.controller.dto.MemberFormDto;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.testutil.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // [1]
@AutoConfigureMockMvc // [1]
@Import({SecurityConfig.class})
@ContextConfiguration(classes = {
        AddMemberMapperImpl.class
})
class AddMemberMapperTest {
    @Autowired
    private AddMemberMapper mapper;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("MemberFormDto To Command 매핑")
    void dtoToCmd(){
        //given
        MemberFormDto dto = TestData.mockMemberFormDto;

        //when
        AddMemberCommand cmd = mapper.dtoToCommand(dto, passwordEncoder);
        //then
        assertAll(
                ()->assertThat(cmd.getMemberId()).isEqualTo(dto.getMemberId()),
                ()->assertThat(cmd.getPassword()).startsWith("$2a$10$"),
                ()->assertThat(cmd.getName()).isEqualTo(dto.getName()),
                ()->assertThat(cmd.getPhone()).isEqualTo(dto.getPhone()),
                ()->assertThat(cmd.getCity()).isEqualTo(dto.getCity()),
                ()->assertThat(cmd.getStreet()).isEqualTo(dto.getStreet()),
                ()->assertThat(cmd.getZipcode()).isEqualTo(dto.getZipcode())
        );
    }
}