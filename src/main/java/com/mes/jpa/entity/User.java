package com.mes.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {
	@Id
	@GeneratedValue
	@Column(name = "user_id", updatable = false)
	private Long id;

	@NotNull
	private String userName;

	private String email;

	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private final List<Purchase> purchases = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private final List<Sales> sales = new ArrayList<>();

	private User(String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	private User(Long id, String userName, String email, String password) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public static User createUser(String userName, String email, String password) {
		return new User(userName, email, password);
	}

	public static User updateUser(Long id, String userName, String email, String password) {
		return new User(id, userName, email, password);
	}
}
