package com.seung.practice.member.repository;

import com.seung.practice.member.domain.model.aggregates.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

	// 회원가입, 수정 시 필요한 저장
	public void save(Member member) {
		em.persist(member);
	}

	// 회원 조회 로직(id : 회원 가입 시 자동  SEQUENCE)
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	// 회원 아이디로 조회
	public List<Member> findByMemberId(String memberId) {
		return em.createQuery("select m from Member m where m.memberId = :memberId", Member.class)
				.setParameter("memberId", memberId)
				.getResultList();
	}

	// 회원 목록
	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}


}