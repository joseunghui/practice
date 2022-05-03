package com.seung.practice.member.controller.dto.mapper;

import com.seung.practice.config.MapstructConfig;
import com.seung.practice.member.controller.dto.MemberFormDto;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Mapper(config = MapstructConfig.class)
public abstract class AddMemberMapper {

    @Mapping(target = "birth", ignore = true)
    @Mapping(target = "password", ignore = true)

    public abstract AddMemberCommand dtoToCommand(MemberFormDto dto, PasswordEncoder pwEnc);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final AddMemberCommand.AddMemberCommandBuilder targetBuilder,
            MemberFormDto dto, PasswordEncoder pwEnc){

		// String 으로 입력받은 생년월일을 Date 로 변경해서 저장
		targetBuilder.birth(LocalDateTime.from(LocalDate.parse(dto.getBirth()).atStartOfDay()));

		// 비밀번호 암호화 상태로 저장
        targetBuilder.password(pwEnc.encode(dto.getPassword()));
    }
}