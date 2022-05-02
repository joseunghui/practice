package com.seung.practice.member.domain.model.aggregates;

import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.domain.model.valueobjects.Address;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor  //TODO : new Member() 부분이 많이 일단 추가. 꼭 필요한 경우에만 남김
public class Member {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "member_id")
	@NotNull
	private String memberId;

	@NotNull
	private String password;

	private String name;

	@Embedded
	private Address address;

	private String phone;
	private String email;
	private String gender;
//	private String birth;
    private LocalDateTime birth;

	public Member(AddMemberCommand command){
		this.memberId = command.getMemberId();
		this.password = command.getPassword();
		this.name = command.getName();
		this.phone = command.getPhone();
		this.email = command.getEmail();
		this.gender = command.getGender();
		this.birth = command.getBirth();
		this.address = new Address(command.getCity(), command.getStreet(), command.getZipcode());
	}

	//TODO : InitDB.java를 위해 남겨둠. 추후 정리해야함.(InitDB.java 가 필요한 코드인지 분석이 안됨)
	public Member(String memberId, String password, String name){
		this.memberId = memberId;
		this.password = password;
		this.name = name;
	}

}