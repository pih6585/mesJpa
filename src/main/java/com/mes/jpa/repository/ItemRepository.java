package com.mes.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mes.jpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
