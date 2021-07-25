package com.mes.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity {

	@Id
	@GeneratedValue
	@Column(name = "customer_id",  updatable = false)
	private Long id;

	private String customerName;

	private String director;

	@Embedded
	private Address address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private final List<Purchase> purchases = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private final List<Sales> sales = new ArrayList<>();

	private Customer(String customerName, String director, Address address) {
		this.customerName = customerName;
		this.director = director;
		this.address = address;
	}

	private Customer(Long id, String customerName, String director, Address address) {
		this.id = id;
		this.customerName = customerName;
		this.director = director;
		this.address = address;
	}

	public static Customer createCustomer(String customerName, String director, Address address) {
		return new Customer(customerName, director, address);
	}

	public static Customer updateCustomer(Long id, String customerName, String director, Address address) {
		return new Customer(id, customerName, director, address);
	}
}
