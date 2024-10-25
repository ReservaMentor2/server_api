package com.reservamentor.repository;

import com.reservamentor.dto.PurchaseReportDTO;
import com.reservamentor.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUserId(Integer userId);

    @Query(value = "SELECT 20, AS quantity, CURRENT_DATE AS consultdate", nativeQuery = true)
    List<Object[]> getPurchaseReportByDate();
}