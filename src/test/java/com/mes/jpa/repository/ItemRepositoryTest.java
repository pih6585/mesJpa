package com.mes.jpa.repository;

import static org.assertj.core.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.mes.jpa.entity.Customer;
import com.mes.jpa.entity.Item;
import com.mes.jpa.entity.ItemType;

@Transactional
@DataJpaTest
class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("제품정보를 저장한다.")
	public void createItem(){
		Item item = Item.createItem("육각볼트", ItemType.ITEM,"2x2","테스툥");

		Item resultItem = itemRepository.save(item);

		entityManager.clear();
		entityManager.flush();

		entityManager.find(Customer.class,resultItem.getId());

		assertThat(resultItem.getItemName()).isEqualTo("육각볼트");
		assertThat(resultItem.getItemType()).isEqualTo(ItemType.ITEM);
	}

	@Test
	@DisplayName("제품정보를 수정한다.")
	public void updateItem(){
		Item item = Item.createItem("육각볼트", ItemType.ITEM,"2x2","테스트");

		Item resultItem = itemRepository.save(item);

		Item updateItem = Item.updateItem(resultItem.getId(), "칠각볼트", ItemType.MATERIAL, "2x2", "테스트");

		assertThat(updateItem.getItemName()).isEqualTo("칠각볼트");
		assertThat(updateItem.getItemType()).isEqualTo(ItemType.MATERIAL);
	}
}