package com.mes.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemStock extends BaseEntity {

	private final String ITEM_STOCK_ID = "stock_id";
	private final String ITEM_ID = "item_id";
	@Id
	@GeneratedValue
	@Column(name = ITEM_STOCK_ID, updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ITEM_ID)
	private Item item;

	@Column(unique = true)
	private String serialNumber;

	private int stockQty;

	private ItemStock(long id, Item item, String serialNumber, int stockQty) {
		this.id = id;
		this.item = item;
		this.serialNumber = serialNumber;
		this.stockQty = stockQty;
		item.getItemStocks().add(this);
	}

	public static ItemStock createStock(long id, Item item, String serialNumber, int stockQty) {
		return new ItemStock(id,item,serialNumber,stockQty);
	}

	public static ItemStock updateStock(Long id, Item item, String serialNumber, int stockQty) {
		return new ItemStock(id,item,serialNumber,stockQty);
	}

	public int addStock(int stockQty) {
		return this.stockQty += stockQty;
	}

	public int minusStock(int stockQty) {
		int calculateQty = this.stockQty - stockQty;
		if(calculateQty < 0){
			throw new IllegalArgumentException("재고수량이 0 이하가 될수 없습니다.");
		}
		return calculateQty;
	}
}
