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

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;

	private String itemName;

	@Enumerated(EnumType.STRING)
	private ItemType itemType;

	private String itemSpec;

	private String remark;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<ItemStock> itemStocks = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<PurchaseItem> purchaseItems = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<SalesItem> salesItems = new ArrayList<>();

}
