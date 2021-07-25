package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemTest {

	@Test
	@DisplayName("아이템 정보를 입력하면 아이템정보가 생성된다.")
	public void createItem() {
		Item createItem = getItem();

		assertThat(createItem.getItemName()).isEqualTo("나사");
		assertThat(createItem.getItemType()).isEqualTo(ItemType.ITEM);
		assertThat(createItem.getItemSpec()).isEqualTo("2x2");
	}

	@Test
	@DisplayName("아이템 정보를 수정하면 아이템정보가 수정된다.")
	public void updateItem() {
		Item createItem = getItem();

		Item updateItem = Item.updateItem(createItem.getId(),createItem.getItemName(), createItem.getItemType(),"2x3","재생산");

		assertThat(updateItem.getItemName()).isEqualTo(createItem.getItemName());
		assertThat(updateItem.getItemSpec()).isEqualTo("2x3");
		assertThat(updateItem.getRemark()).isEqualTo("재생산");
	}

	private Item getItem() {
		return Item.createItem("나사",ItemType.ITEM,"2x2","서브자재");
	}

}