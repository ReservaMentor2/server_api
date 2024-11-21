package com.reservamentor.repository;

import com.reservamentor.model.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.JpaParameters;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Integer> {
}
