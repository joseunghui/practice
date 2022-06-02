package com.seung.practice.member.controller.dto.mapper;

import com.seung.practice.member.controller.dto.ModifyMemberFormDto;
import com.seung.practice.member.domain.model.commands.ModifyMemberCommand;
import com.seung.practice.member.domain.model.commands.ModifyMemberCommand.ModifyMemberCommandBuilder;
import javax.annotation.processing.Generated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-02T11:18:27+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class ModifyMemberMapperImpl extends ModifyMemberMapper {

    @Override
    public ModifyMemberCommand dtoToCommand(ModifyMemberFormDto dto, PasswordEncoder pwEnc) {
        if ( dto == null && pwEnc == null ) {
            return null;
        }

        ModifyMemberCommandBuilder modifyMemberCommand = ModifyMemberCommand.builder();

        if ( dto != null ) {
            modifyMemberCommand.memberId( dto.getMemberId() );
            modifyMemberCommand.name( dto.getName() );
            modifyMemberCommand.phone( dto.getPhone() );
            modifyMemberCommand.email( dto.getEmail() );
            modifyMemberCommand.gender( dto.getGender() );
            modifyMemberCommand.city( dto.getCity() );
            modifyMemberCommand.street( dto.getStreet() );
            modifyMemberCommand.zipcode( dto.getZipcode() );
        }

        afterMappingToCommand( modifyMemberCommand, dto, pwEnc );

        return modifyMemberCommand.build();
    }
}
