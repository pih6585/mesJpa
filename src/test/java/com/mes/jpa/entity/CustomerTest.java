package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerTest {

	private Customer customer;

	@BeforeEach
	void setup(){
		customer = new Customer();
	}

	@Test
	@DisplayName("거래처의 정보를 입력하면 거래처가 생성된다.")
	public void createCustomer(){
		Address address = new Address("천중로","1동","11-11");
		Customer result = getCustomer(1L,"네이버","홍길동",address);

		assertThat(result.getId()).isEqualTo(1L);
		assertThat(result.getCustomerName()).isEqualTo("네이버");
		assertThat(result.getAddress().getStreet()).isEqualTo("1동");
	}

	@Test
	@DisplayName("거래처의 정보를 수정하면 거래처가 변경된다.")
	public void updateCustomer(){
		Address address = new Address("천중로","1동","11-11");
		Customer result = getCustomer(2L,"라인","손오공",address);

		Customer updateCustomer = result.updateCustomer("카카오","홍길동",result.getAddress());

		assertThat(updateCustomer.getId()).isEqualTo(result.getId());
		assertThat(updateCustomer.getCustomerName()).isEqualTo("카카오");
		assertThat(updateCustomer.getAddress().getStreet()).isEqualTo("1동");
	}

	private Customer getCustomer(long id, String customerName, String director, Address address) {
		return customer.createCustomer(id,customerName,director,address);
	}
}