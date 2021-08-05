package com.mes.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mes.jpa.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
