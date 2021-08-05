package com.mes.jpa.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import com.mes.jpa.entity.Item;
import com.mes.jpa.entity.ItemStock;
import com.mes.jpa.entity.ItemType;

@Transactional
@DataJpaTest
class ItemStockRepositoryTest {

	@Autowired
	ItemStockRepository itemStockRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	EntityManager entityManager;

	@Test
	@DisplayName("재고 저장시 재고가 저장된다")
	public void createItemStock() {
		Item item = Item.createItem("육각볼트", ItemType.ITEM, "2x2", "테스트");
		Item saveItem = itemRepository.save(item);

		ItemStock itemStock = ItemStock.createStock(saveItem, "123456789", 10);
		ItemStock saveItemStock = itemStockRepository.save(itemStock);

		entityManager.flush();
		entityManager.clear();

		Optional<ItemStock> optItemStock = itemStockRepository.findById(saveItemStock.getId());

		if (optItemStock.isPresent()) {
			assertThat(optItemStock.get().getStockQty()).isEqualTo(10);
			assertThat(optItemStock.get().getSerialNumber()).isEqualTo("123456789");
			assertThat(optItemStock.get().getItem().getItemName()).isEqualTo("육각볼트");
		}
	}

	@Test
	@DisplayName("재고 저장후 수정시 재고가 수정된다")
	public void updateItemStock() {
		Item item = Item.createItem("육각볼트", ItemType.ITEM, "2x2", "테스트");
		Item saveItem = itemRepository.save(item);

		ItemStock itemStock = ItemStock.createStock(saveItem, "123456789", 10);
		ItemStock saveItemStock = itemStockRepository.save(itemStock);

		ItemStock updateStock = ItemStock.updateStock(saveItemStock.getId(), saveItemStock.getItem(), "22222222", 20);

		ItemStock resultItemStock = itemStockRepository.save(updateStock);

		entityManager.flush();
		entityManager.clear();

		Optional<ItemStock> optItemStock = itemStockRepository.findById(resultItemStock.getId());

		if (optItemStock.isPresent()) {
			assertThat(optItemStock.get().getStockQty()).isEqualTo(20);
			assertThat(optItemStock.get().getSerialNumber()).isEqualTo("22222222");
			assertThat(optItemStock.get().getItem().getItemName()).isEqualTo("육각볼트");
		}
	}
}