package com.mes.jpa.entity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity{

	private final String ITEM_ID = "item_id";
	@Id
	@GeneratedValue
	@Column(name = ITEM_ID, updatable = false)
	private Long id;

	private String itemName;

	@Enumerated(EnumType.STRING)
	private ItemType itemType;

	private String itemSpec;

	private String remark;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private final List<ItemStock> itemStocks = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private final List<PurchaseItem> purchaseItems = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private final List<SalesItem> salesItems = new ArrayList<>();

	private Item(long id, String itemName, ItemType itemType, String itemSpec, String remark) {
		this.id = id;
		this.itemName = itemName;
		this.itemType = itemType;
		this.itemSpec = itemSpec;
		this.remark = remark;
	}

	public static Item createItem(Long id, String itemName, ItemType itemType, String itemSpec, String remark) {
		return new Item(id,itemName,itemType,itemSpec,remark);
	}

	public static Item updateItem(Long id, String itemName, ItemType itemType, String itemSpec, String remark) {
		return new Item(id,itemName,itemType,itemSpec,remark);
	}
}
