package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseTest {

	@Test
	@DisplayName("구매정보를 입력하면 구매정보가 생성된다.")
	public void createPurchase() {
		Purchase purchase = getPurchase();

		assertThat(purchase.getCustomer().getCustomerName()).isEqualTo("카카오");
		assertThat(purchase.getCustomer().getDirector()).isEqualTo("리키타");

		assertThat(purchase.getUser().getUserName()).isEqualTo("박일한");
	}

	@Test
	@DisplayName("구매정보의 거래처를 수정하면 구매 거래처 정보가 수정된다.")
	public void updatePurchaseByCustomer() {
		Purchase purchase = getPurchase();

		Customer customer = Customer.createCustomer("네이버", "홍길동", getAddress());
		Purchase updatePurchase = Purchase.updatePurchase(purchase.getId(), customer, purchase.getUser());

		assertThat(updatePurchase.getCustomer().getCustomerName()).isEqualTo("네이버");
		assertThat(updatePurchase.getCustomer().getDirector()).isEqualTo("홍길동");
	}

	@Test
	@DisplayName("구매정보의 유저를 수정하면 구매 유저 정보가 수정된다.")
	public void updatePurchaseByUser() {
		Purchase purchase = getPurchase();

		User user = User.createUser("손오공", "abc@naver.com", "12345");
		Purchase updatePurchase = Purchase.updatePurchase(purchase.getId(), purchase.getCustomer(), user);

		assertThat(updatePurchase.getUser().getUserName()).isEqualTo("손오공");
		assertThat(updatePurchase.getUser().getEmail()).isEqualTo("abc@naver.com");
	}

	@Test
	@DisplayName("구매정보의 거래처는 구매정보를 포함하고있다.")
	public void PurchaseByCustomerContains() {
		Customer customer = getPurchase().getCustomer();
		assertThat(customer.getPurchases()).extracting("customer.customerName").containsExactly("카카오");
		assertThat(customer.getPurchases()).extracting("customer.director").containsExactly("리키타");
	}

	@Test
	@DisplayName("구매정보의 유저는 구매정보를 포함하고있다.")
	public void PurchaseByUserContains() {
		User user = getPurchase().getUser();

		assertThat(user.getPurchases()).extracting("user.userName").containsExactly("박일한");
		assertThat(user.getPurchases()).extracting("user.email").containsExactly("aaa@naver.com");
	}

	private Purchase getPurchase() {
		return Purchase.createPurchase(getCustomer(), getUser());
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