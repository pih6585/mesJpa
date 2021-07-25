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
public class PurchaseItem extends BaseEntity {

	private static final String PURCHASE_ITEM_ID = "purchase_item_id";
	private static final String ITEM_ID = "item_id";
	private static final String PURCHASE_ID = "purchase_id";

	@Id
	@GeneratedValue
	@Column(name = PURCHASE_ITEM_ID)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = ITEM_ID)
	private Item item;

	private int qty;

	private int prc;

	private int amt;

	private String serialNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = PURCHASE_ID)
	private Purchase purchase;

	private PurchaseItem(Item item, int qty, int prc, int amt, String serialNumber, Purchase purchase) {
		this.item = item;
		item.getPurchaseItems().add(this);
		this.qty = qty;
		this.prc = prc;
		this.amt = amt;
		this.serialNumber = serialNumber;
		this.purchase = purchase;
		purchase.getPurchaseItems().add(this);
	}

	private PurchaseItem(Long id, Item item, int qty, int prc, int amt, String serialNumber, Purchase purchase) {
		this.id = id;
		this.item = item;
		item.getPurchaseItems().add(this);
		this.qty = qty;
		this.prc = prc;
		this.amt = amt;
		this.serialNumber = serialNumber;
		this.purchase = purchase;
		purchase.getPurchaseItems().add(this);
	}

	public static PurchaseItem createPurchaseItem(Item item, int qty, int prc, int amt, String serialNumber,
		Purchase purchase) {
		return new PurchaseItem(item, qty, prc, amt, serialNumber, purchase);
	}

	public static PurchaseItem updatePurchaseItem(Long id, Item item, int qty, int prc, int amt, String serialNumber, Purchase purchase) {
		return new PurchaseItem(id, item, qty, prc, amt, serialNumber, purchase);
	}
}
