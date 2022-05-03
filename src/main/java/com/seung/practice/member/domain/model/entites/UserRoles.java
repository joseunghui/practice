package com.seung.practice.member.domain.model.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	private String authority;

	private Long mid;
}
