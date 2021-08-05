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

	@Id
	@GeneratedValue
	@Column(name = "stock_id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	@Column(unique = true)
	private String serialNumber;

	private int stockQty;

	public ItemStock(Item item, String serialNumber, int stockQty) {
		this.item = item;
		this.serialNumber = serialNumber;
		this.stockQty = stockQty;
		item.getItemStocks().add(this);
	}

	private ItemStock(long id, Item item, String serialNumber, int stockQty) {
		this.id = id;
		this.item = item;
		this.serialNumber = serialNumber;
		this.stockQty = stockQty;
		item.getItemStocks().add(this);
	}

	public static ItemStock createStock(Item item, String serialNumber, int stockQty) {
		return new ItemStock(item, serialNumber, stockQty);
	}

	public static ItemStock updateStock(Long id, Item item, String serialNumber, int stockQty) {
		return new ItemStock(id, item, serialNumber, stockQty);
	}

	public int addStock(int stockQty) {
		return this.stockQty += stockQty;
	}

	public int minusStock(int stockQty) {
		int calculateQty = this.stockQty - stockQty;
		if (calculateQty < 0) {
			throw new IllegalArgumentException("재고수량이 0 이하가 될수 없습니다.");
		}
		return calculateQty;
	}
}
