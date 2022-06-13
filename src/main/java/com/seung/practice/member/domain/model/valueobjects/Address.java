package com.seung.practice.member.domain.model.valueobjects;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

	// 주소를 3개로 나눠서 입력 후 하나의 Address 로 저장 
	private String city;
	private String street;
	private String zipcode;

	protected Address() {

	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}