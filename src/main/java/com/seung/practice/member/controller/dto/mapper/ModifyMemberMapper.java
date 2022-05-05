package com.seung.practice.member.controller.dto.mapper;

import com.seung.practice.common.config.MapstructConfig;
import com.seung.practice.member.controller.dto.ModifyMemberFormDto;
import com.seung.practice.member.domain.model.commands.ModifyMemberCommand;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper(config = MapstructConfig.class)
public abstract class ModifyMemberMapper {

    @Mapping(target = "birth", ignore = true)
    @Mapping(target = "password", ignore = true)

    public abstract ModifyMemberCommand dtoToCommand(ModifyMemberFormDto dto, PasswordEncoder pwEnc);

    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final ModifyMemberCommand.ModifyMemberCommandBuilder targetBuilder,
            ModifyMemberFormDto dto, PasswordEncoder pwEnc) {

        // 생년월일 : String -> LocalDateTime 타입 변경
        targetBuilder.birth(LocalDateTime.from(LocalDate.parse(dto.getBirth()).atStartOfDay()));

        // 비밀번호를 암호화된 상태로 저장
        targetBuilder.password(pwEnc.encode(dto.getPassword()));
    }

}
