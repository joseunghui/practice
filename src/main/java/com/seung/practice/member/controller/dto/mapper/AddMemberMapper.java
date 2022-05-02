package com.seung.practice.member.controller.dto.mapper;

import com.seung.practice.config.MapstructConfig;
import com.seung.practice.member.controller.dto.MemberFormDto;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(config = MapstructConfig.class)
public abstract class AddMemberMapper {


    @Mapping(target = "birth", ignore = true)
    @Mapping(target = "password", ignore = true)
    public abstract AddMemberCommand dtoToCommand(MemberFormDto dto, PasswordEncoder pwEnc);


    @AfterMapping
    protected void afterMappingToCommand(
            @MappingTarget final AddMemberCommand.AddMemberCommandBuilder targetBuilder,
            MemberFormDto dto, PasswordEncoder pwEnc){
        //TODO
        //birth를 String으로 받아서 LocalDateTime으로 변환 하는 것을 해보면 좋겠다고 생각 했습니다.
        //이곳에서 구현해 줍니다.
        //AfterMapping 사용법은 구글링 하면 쉽게 찾을 수 있습니다.
        targetBuilder.password(pwEnc.encode(dto.getPassword()));
    }
}