package com.seung.practice.Goods.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;

@Entity
@Getter @Setter
public class Goods {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "goods_id")
	@NotNull
	private Long id;

	@NotNull
	private String title;

	@NotNull
	private int price;

	private File image;
	private String imageName;

	private String colorType;

}
