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
public class SalesItem extends BaseEntity{

	@Id
	@GeneratedValue
	@Column(name = "sales_item_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;

	private int qty;

	private int prc;

	private int amt;

	private String serialNumber;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sales_id")
	private Sales sales;
}
