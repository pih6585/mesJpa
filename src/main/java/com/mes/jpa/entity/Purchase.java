package com.mes.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Purchase extends BaseEntity {

	private static final String PURCHASE_ID = "purchase_id";
	private static final String CUSTOMER_ID = "customer_id";
	private static final String USER_ID = "user_id";
	@Id @GeneratedValue
	@Column(name = PURCHASE_ID)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = CUSTOMER_ID)
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = USER_ID)
	private User user;

	@OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
	private final List<PurchaseItem> purchaseItems = new ArrayList<>();
}
