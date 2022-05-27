package com.seung.practice.repository;


import com.seung.practice.member.domain.model.aggregates.Member;
import com.seung.practice.member.domain.model.commands.AddMemberCommand;
import com.seung.practice.member.repository.MemberRepository;
import com.seung.practice.testutil.TestData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;


@Transactional
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Rollback(value = true)
	void save() {
		Member member = TestData.mockMember;

		Member savedMember = memberRepository.save(member);

		Assertions.assertAll(
				() -> assertThat(savedMember.getMemberId()).isEqualTo(member.getMemberId()),
				() -> assertThat(savedMember.getPassword()).isEqualTo(member.getPassword()),
				() -> assertThat(savedMember.getName()).isEqualTo(member.getName()),
				() -> assertThat(savedMember.getEmail()).isEqualTo(member.getEmail()),
				() -> assertThat(savedMember.getBirth()).isEqualTo(member.getBirth()),
				() -> assertThat(savedMember.getGender()).isEqualTo(member.getGender()),
				() -> assertThat(savedMember.getPhone()).isEqualTo(member.getPhone()),
				() -> assertThat(savedMember.getAddress().getStreet()).isEqualTo(member.getAddress().getStreet()),
				() -> assertThat(savedMember.getAddress().getCity()).isEqualTo(member.getAddress().getCity()),
				() -> assertThat(savedMember.getAddress().getZipcode()).isEqualTo(member.getAddress().getZipcode())
		);
	}

	@Test
	@Rollback(value = true)
	void findOne() {
		Member member = memberRepository.save(TestData.mockMember);

		// TODO : 여기 다시 생각..... 생각하자....
//		assertThat(memberRepository.findOne(member.getId()).get()).isEqualTo(member) ?????????
	}

	@Test
	@Rollback(value = true)
	void findByMemberId() {
		// 회원 생성
		Member member = TestData.mockMember;
		Member savedMember = memberRepository.save(member);

		assertThat(memberRepository.findByMemberId(member.getMemberId()).get()).isEqualTo(savedMember);
	}

	@Test
	@Rollback(value = true)
	void findAll() {
		// 회원 생성
		Member member = TestData.mockMember;
		Member savedMember = memberRepository.save(member);

		List<Member> findMemberList = memberRepository.findAll();

		assertThat(memberRepository.findAll()).isEqualTo(findMemberList);

	}

}