package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SalesItemTest {
	@Test
	@DisplayName("판매제품정보를 입력하면 생성된다.")
	public void createSales(){
		SalesItem salesItem = getSalesItem();

		assertThat(salesItem.getQty()).isEqualTo(2);
		assertThat(salesItem.getPrc()).isEqualTo(500);
		assertThat(salesItem.getAmt()).isEqualTo(1000);
		assertThat(salesItem.getItem().getItemName()).isEqualTo("볼트");
		assertThat(salesItem.getSales().getCustomer().getCustomerName()).isEqualTo("네이버");
	}

	@Test
	@DisplayName("구매제품정보를 수정하면 수정된다.")
	public void updateSales(){
		SalesItem salesItem = getSalesItem();
		SalesItem updateSalesItem = SalesItem.updateSalesItem(salesItem.getId(), getItem(), 5, 100, 500,
			"1234567", getSales());

		assertThat(updateSalesItem.getQty()).isEqualTo(5);
		assertThat(updateSalesItem.getPrc()).isEqualTo(100);
		assertThat(updateSalesItem.getAmt()).isEqualTo(500);
		assertThat(updateSalesItem.getItem().getItemName()).isEqualTo("볼트");
		assertThat(updateSalesItem.getSales().getCustomer().getCustomerName()).isEqualTo("네이버");
	}

	private Item getItem(){
		return Item.createItem("볼트",ItemType.ITEM,"2x2","테스트");
	}

	private Sales getSales(){
		Customer customer =  Customer.createCustomer("네이버","홍길동",new Address("천호","서울","11"));
		User user = User.createUser("박일한","123@naver.com","1234");
		return Sales.createSales(customer,user);
	}

	private SalesItem getSalesItem(){
		return SalesItem.createSalesItem(getItem(),2,500,1000,"1234567",getSales());
	}
}