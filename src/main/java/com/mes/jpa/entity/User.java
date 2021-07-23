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

	private final String USER_ID = "user_id";
	@Id
	@GeneratedValue
	@Column(name = USER_ID)
	private Long id;

	@NotNull
	private String userName;

	private String email;

	private String password;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private final List<Purchase> purchases = new ArrayList<>();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private final List<Sales> sales = new ArrayList<>();

	public User(long id, String userName, String email, String password) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public User createUser(long id, String userName, String email, String password) {
		return new User(id, userName, email, password);
	}

	public User updateUser(String userName, String email, String password) {
		return new User(id, userName, email, password);
	}
}
