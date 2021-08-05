package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemStockTest {

	@Test
	@DisplayName("제품정보 및 수량 및 시리얼 번호를 입력하면 재고가 저장된다.")
	public void createStock() {
		Item item = Item.createItem("1자드라이버", ItemType.MATERIAL, "1x2", "테스트자재");

		ItemStock result = getItemStock(item, "1234", 100);
		assertThat(result.getItem()).isEqualTo(item);
		assertThat(result.getId()).isEqualTo(1L);
	}

	@Test
	@DisplayName("재고정보를 수정하면 재고가 수정된다.")
	public void updateStock() {
		Item item = Item.createItem("1자드라이버", ItemType.MATERIAL, "1x2", "테스트자재");

		ItemStock result = getItemStock(item, "1234", 100);
		ItemStock updateItemStock = ItemStock.updateStock(2L, result.getItem(), result.getSerialNumber(), 150);

		assertThat(updateItemStock.getStockQty()).isEqualTo(150);
		assertThat(updateItemStock).isEqualTo(result);
	}

	@Test
	@DisplayName("재고수량을 추가하면 재고가 증가된다.")
	public void addStock() {
		Item item = Item.createItem("1자드라이버", ItemType.MATERIAL, "1x2", "테스트자재");
		ItemStock result = getItemStock(item, "1234", 100);

		int addStockValue = result.addStock(200);

		assertThat(addStockValue).isEqualTo(300);
	}

	@Test
	@DisplayName("재고수량을 삭제하면 재고가 감소된다.")
	public void minusStock() {
		Item item = Item.createItem("1자드라이버", ItemType.MATERIAL, "1x2", "테스트자재");
		ItemStock result = getItemStock(item, "1234", 200);

		int minusStockValue = result.minusStock(100);

		assertThat(minusStockValue).isEqualTo(100);
	}

	@Test
	@DisplayName("재고수량을 감소시 0보다 작으면  예외가 발생된다.")
	public void minusStock_zero_check() {
		Item item = Item.createItem("1자드라이버", ItemType.MATERIAL, "1x2", "테스트자재");
		ItemStock result = getItemStock(item, "4586", 100);

		assertThatThrownBy(() -> result.minusStock(200))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("재고수량이 0 이하가 될수 없습니다.");
	}

	@Test
	@DisplayName("재고에 저장된 제품정보와 제품에 저장된 재고 정보가 포함되어 있다.")
	public void itemsStockByItemContainsItemByItemStock() {
		Item item = Item.createItem("1자드라이버", ItemType.MATERIAL, "1x2", "테스트자재");
		getItemStock(item, "4586", 100);
		getItemStock(item, "4885", 50);

		List<ItemStock> itemStocks = item.getItemStocks();
		assertThat(itemStocks).extracting("serialNumber").containsExactly("4586", "4885");
		assertThat(itemStocks).extracting("stockQty").containsExactly(100, 50);
	}

	private ItemStock getItemStock(Item item, String serialNumber, int stockQty) {
		return ItemStock.createStock(item, serialNumber, stockQty);
	}
}