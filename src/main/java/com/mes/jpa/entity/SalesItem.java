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
public class SalesItem extends BaseEntity {

	private static final String SALES_ITEM_ID = "sales_item_id";
	private static final String ITEM_ID = "item_id";
	private static final String SALES_ID = "sales_id";
	@Id
	@GeneratedValue
	@Column(name = SALES_ITEM_ID)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ITEM_ID)
	private Item item;

	private int qty;

	private int prc;

	private int amt;

	private String serialNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = SALES_ID)
	private Sales sales;

	private SalesItem(Item item, int qty, int prc, int amt, String serialNumber, Sales sales) {
		this.item = item;
		item.getSalesItems().add(this);
		this.qty = qty;
		this.prc = prc;
		this.amt = amt;
		this.serialNumber = serialNumber;
		this.sales = sales;
		sales.getSalesItems().add(this);
	}

	private SalesItem(Long id, Item item, int qty, int prc, int amt, String serialNumber, Sales sales) {
		this.id = id;
		this.item = item;
		item.getSalesItems().add(this);
		this.qty = qty;
		this.prc = prc;
		this.amt = amt;
		this.serialNumber = serialNumber;
		this.sales = sales;
		sales.getSalesItems().add(this);
	}

	public static SalesItem createSalesItem(Item item, int qty, int prc, int amt, String serialNumber,
		Sales sales) {
		return new SalesItem(item, qty, prc, amt, serialNumber, sales);
	}

	public static SalesItem updateSalesItem(Long id, Item item, int qty, int prc, int amt, String serialNumber,
		Sales sales) {
		return new SalesItem(id, item, qty, prc, amt, serialNumber, sales);
	}

}
