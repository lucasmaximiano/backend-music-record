package com.music.record.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.music.record.model.SaleItem;

public interface SaleItemRepository extends JpaRepository<SaleItem, Integer> {

}
