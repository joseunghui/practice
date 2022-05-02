package com.seung.practice;

import com.seung.practice.member.domain.model.aggregates.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDB {

	private final InitService initService;


	@PostConstruct
	public void init() {
		initService.dbInit1();
		initService.dbInit2();
	}

	@Component
	@Transactional
	@RequiredArgsConstructor
	static class InitService {

		private final EntityManager em;

		public void dbInit1() {
			Member member1 = createMember("", "", "");
			em.persist(member1);
		}

		public void dbInit2() {
			Member member2 = createMember("", "", "");
			em.persist(member2);
		}


		private Member createMember(String memberId, String password, String name) {
			return new Member(memberId, password, name);
		}
	}
}