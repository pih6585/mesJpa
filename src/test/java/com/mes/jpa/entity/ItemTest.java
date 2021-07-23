package com.mes.jpa.entity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemTest {

	private Item item;

	@BeforeEach
	void setup(){
		item = new Item();
	}


	@Test
	@DisplayName("아이템 정보를 입력하면 아이템정보가 생성된다.")
	public void createItem() {
		Item createItem = getItem(1L,"볼트",ItemType.자재,"1x2","미생산자재");

		assertThat(createItem.getId()).isEqualTo(1L);
		assertThat(createItem.getItemName()).isEqualTo("볼트");
	}

	@Test
	@DisplayName("아이템 정보를 수정하면 아이템정보가 수정된다.")
	public void updateItem() {
		Item createItem = getItem(2L,"나사",ItemType.제품,"2x2","서브자재");

		Item updateItem = createItem.updateItem(createItem.getItemName(), createItem.getItemType(),"2x3","재생산");

		assertThat(updateItem.getItemName()).isEqualTo(createItem.getItemName());
		assertThat(updateItem.getItemSpec()).isEqualTo("2x3");
		assertThat(updateItem.getRemark()).isEqualTo("재생산");
	}

	private Item getItem(long id, String itemName, ItemType itemType, String itemSpec, String remark) {
		return item.createItem(id, itemName, itemType, itemSpec, remark);
	}

}