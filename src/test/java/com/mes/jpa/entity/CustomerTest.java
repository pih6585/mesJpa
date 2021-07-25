package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {

	@Test
	@DisplayName("거래처의 정보를 입력하면 거래처가 생성된다.")
	public void createCustomer(){
		Customer result = Customer.createCustomer("네이버","손오공",getAddress());

		assertThat(result.getCustomerName()).isEqualTo("네이버");
		assertThat(result.getAddress().getStreet()).isEqualTo("1동");
	}

	@Test
	@DisplayName("거래처의 정보를 수정하면 거래처가 변경된다.")
	public void updateCustomer(){
		Customer result =Customer.createCustomer("네이버","손오공",getAddress());

		Customer updateResult = Customer.updateCustomer(result.getId(), "카카오", "홍길동", getAddress());

		assertThat(updateResult.getId()).isEqualTo(result.getId());
		assertThat(updateResult.getCustomerName()).isEqualTo("카카오");
		assertThat(updateResult.getAddress().getStreet()).isEqualTo("1동");
	}

	private Address getAddress() {
		return new Address("천중로","1동","11-11");
	}
}