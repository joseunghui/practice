package com.seung.practice.member.controller.dto.mapper;

import com.seung.practice.member.controller.dto.AddMemberFormDto;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.domain.model.commands.AddMemberCommand.AddMemberCommandBuilder;
import javax.annotation.processing.Generated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-02T11:18:27+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class AddMemberMapperImpl extends AddMemberMapper {

    @Override
    public AddMemberCommand dtoToCommand(AddMemberFormDto dto, PasswordEncoder pwEnc) {
        if ( dto == null && pwEnc == null ) {
            return null;
        }

        AddMemberCommandBuilder addMemberCommand = AddMemberCommand.builder();

        if ( dto != null ) {
            addMemberCommand.memberId( dto.getMemberId() );
            addMemberCommand.name( dto.getName() );
            addMemberCommand.phone( dto.getPhone() );
            addMemberCommand.email( dto.getEmail() );
            addMemberCommand.gender( dto.getGender() );
            addMemberCommand.city( dto.getCity() );
            addMemberCommand.street( dto.getStreet() );
            addMemberCommand.zipcode( dto.getZipcode() );
        }

        afterMappingToCommand( addMemberCommand, dto, pwEnc );

        return addMemberCommand.build();
    }
}
