package com.seung.practice.member.repository;

import com.seung.practice.member.domain.model.aggregates.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	// 회원 아이디로 Member 객체 조회
    Optional<Member> findByMemberId(String memberId);
}
