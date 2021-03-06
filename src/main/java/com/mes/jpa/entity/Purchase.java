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

	@Id
	@GeneratedValue
	@Column(name = "purchase_id",  updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY)
	private final List<PurchaseItem> purchaseItems = new ArrayList<>();

	private Purchase(Customer customer, User user) {
		this.customer = customer;
		customer.getPurchases().add(this);
		this.user = user;
		user.getPurchases().add(this);
	}

	private Purchase(Long id, Customer customer, User user) {
		this.id = id;
		this.customer = customer;
		customer.getPurchases().add(this);
		this.user = user;
		user.getPurchases().add(this);
	}

	public static Purchase createPurchase(Customer customer, User user) {
		return new Purchase(customer, user);
	}

	public static Purchase updatePurchase(Long id, Customer customer, User user) {
		return new Purchase(id, customer, user);
	}
}
