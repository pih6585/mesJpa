package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SalesTest {

	@Test
	@DisplayName("판매정보를 입력하면 판매보가 생성된다.")
	public void createSales() {
		Sales sales = getSales();

		assertThat(sales.getCustomer().getCustomerName()).isEqualTo("카카오");
		assertThat(sales.getCustomer().getDirector()).isEqualTo("리키타");

		assertThat(sales.getUser().getUserName()).isEqualTo("박일한");
	}

	@Test
	@DisplayName("판매정보의 거래처를 수정하면 판매 거래처 정보가 수정된다.")
	public void updateSalesByCustomer() {
		Sales sales = getSales();

		Customer customer = Customer.createCustomer("네이버", "홍길동", getAddress());
		Sales updateSales = Sales.updateSales(sales.getId(), customer, sales.getUser());

		assertThat(updateSales.getCustomer().getCustomerName()).isEqualTo("네이버");
		assertThat(updateSales.getCustomer().getDirector()).isEqualTo("홍길동");
	}

	@Test
	@DisplayName("판매정보의 유저를 수정하면 판매 유저 정보가 수정된다.")
	public void updateSalesByUser() {
		Sales sales = getSales();

		User user = User.createUser("손오공", "abc@naver.com", "12345");
		Sales updateSales = Sales.updateSales(sales.getId(), sales.getCustomer(), user);

		assertThat(updateSales.getUser().getUserName()).isEqualTo("손오공");
		assertThat(updateSales.getUser().getEmail()).isEqualTo("abc@naver.com");
	}

	@Test
	@DisplayName("판매정보의 거래처는 판매정보를 포함하고있다.")
	public void salesByCustomerContains() {
		Customer customer = getSales().getCustomer();
		assertThat(customer.getSales()).extracting("customer.customerName").containsExactly("카카오");
		assertThat(customer.getSales()).extracting("customer.director").containsExactly("리키타");
	}

	@Test
	@DisplayName("구매정보의 유저는 구매정보를 포함하고있다.")
	public void salesByUserContains() {
		User user = getSales().getUser();

		assertThat(user.getSales()).extracting("user.userName").containsExactly("박일한");
		assertThat(user.getSales()).extracting("user.email").containsExactly("aaa@naver.com");
	}

	private Sales getSales() {
		return Sales.createSales(getCustomer(), getUser());
	}

	public Customer getCustomer(){
		return  Customer.createCustomer("카카오", "리키타", getAddress());
	}

	public User getUser(){
		return  User.createUser("박일한", "aaa@naver.com", "123456");
	}

	private Address getAddress() {
		return new Address("서울", "천중로", "11-11");
	}

}