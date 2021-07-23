package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	private User user;

	@BeforeEach
	void setup(){
		user = new User();
	}

	@Test
	@DisplayName("유저정보를 입력하면 유저가 생성 된다.")
	public void createUser(){
		User result = getUser(1L,"테스트","aaa.naver.com","12345");

		assertThat(result.getId()).isEqualTo(1L);
		assertThat(result.getUserName()).isEqualTo("테스트");
	}

	@Test
	@DisplayName("생성된 유저정보를 수정하면 정보가수정된다.")
	public void updateUser(){
		User result = getUser(2L,"테스트3","aaa.google.com","4885");

		User result2 = result.updateUser("테스트2",result.getEmail(),result.getPassword());

		assertThat(result2.getUserName()).isEqualTo("테스트2");

		assertThat(result2.getId()).isEqualTo(result.getId());

	}

	private User getUser(Long id, String userName, String email, String password) {
		return user.createUser(id,userName,email,password);
	}
}