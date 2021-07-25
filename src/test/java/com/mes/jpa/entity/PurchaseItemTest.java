package com.mes.jpa.entity;


import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PurchaseItemTest {

	@Test
	@DisplayName("구매제품정보를 입력하면 생성된다.")
	public void createPurchase(){
		PurchaseItem purchaseItem = getPurchaseItem();

		assertThat(purchaseItem.getQty()).isEqualTo(2);
		assertThat(purchaseItem.getPrc()).isEqualTo(500);
		assertThat(purchaseItem.getAmt()).isEqualTo(1000);
		assertThat(purchaseItem.getItem().getItemName()).isEqualTo("볼트");
		assertThat(purchaseItem.getPurchase().getCustomer().getCustomerName()).isEqualTo("네이버");
	}

	@Test
	@DisplayName("구매제품정보를 수정하면 수정된다.")
	public void updatePurchase(){
		PurchaseItem purchaseItem = getPurchaseItem();
		PurchaseItem updatePurchaseItem = PurchaseItem.updatePurchaseItem(purchaseItem.getId(), getItem(), 5, 100, 500,
			"1234567", getPurchase());

		assertThat(updatePurchaseItem.getQty()).isEqualTo(5);
		assertThat(updatePurchaseItem.getPrc()).isEqualTo(100);
		assertThat(updatePurchaseItem.getAmt()).isEqualTo(500);
		assertThat(updatePurchaseItem.getItem().getItemName()).isEqualTo("볼트");
		assertThat(updatePurchaseItem.getPurchase().getCustomer().getCustomerName()).isEqualTo("네이버");
	}

	private Item getItem(){
		return Item.createItem("볼트",ItemType.ITEM,"2x2","테스트");
	}

	private Purchase getPurchase(){
		Customer customer =  Customer.createCustomer("네이버","홍길동",new Address("천호","서울","11"));
		User user = User.createUser("박일한","123@naver.com","1234");
		return Purchase.createPurchase(customer,user);
	}

	private PurchaseItem getPurchaseItem(){
		return PurchaseItem.createPurchaseItem(getItem(),2,500,1000,"1234567",getPurchase());
	}


}