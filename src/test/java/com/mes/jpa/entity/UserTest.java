package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	@DisplayName("유저정보를 입력하면 유저가 생성 된다.")
	public void createUser(){
		User result = getUser();

		assertThat(result.getUserName()).isEqualTo("테스트3");
	}

	@Test
	@DisplayName("생성된 유저정보를 수정하면 정보가수정된다.")
	public void updateUser(){
		User result = getUser();

		User result2 = User.updateUser(result.getId(),"테스트2",result.getEmail(),result.getPassword());

		assertThat(result2.getUserName()).isEqualTo("테스트2");
	}

	private User getUser() {
		return User.createUser("테스트3","aaa.google.com","4885");
	}
}