package com.seung.practice.member.domain.model.aggregates;

import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.domain.model.entites.UserRoles;
import com.seung.practice.member.domain.model.valueobjects.Address;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor  //TODO : new Member() 부분이 많이 일단 추가. 꼭 필요한 경우에만 남김
public class Member implements UserDetails {
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

    private LocalDateTime birth;

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinColumn(name = "mid")
	@Builder.Default
	private List<String> roles = new ArrayList<>();


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


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}