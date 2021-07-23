package com.mes.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity{

	@Id @GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String userName;

	private String email;

	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Purchase> purchases = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Sales> sales = new ArrayList<>();




}
