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
public class Sales extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "sales_id",  updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "sales", fetch = FetchType.LAZY)
	private final List<SalesItem> salesItems = new ArrayList<>();

	private Sales(Customer customer, User user) {
		this.customer = customer;
		customer.getSales().add(this);
		this.user = user;
		user.getSales().add(this);
	}

	private Sales(Long id, Customer customer, User user) {
		this.id = id;
		this.customer = customer;
		customer.getSales().add(this);
		this.user = user;
		user.getSales().add(this);
	}

	public static Sales createSales(Customer customer, User user) {
		return new Sales(customer, user);
	}

	public static Sales updateSales(Long id, Customer customer, User user) {
		return new Sales(id, customer, user);
	}
}
