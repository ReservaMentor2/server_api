package com.reservamentor.service;

import com.reservamentor.dto.PurchaseCreateUpdateDTO;
import com.reservamentor.dto.PurchaseDTO;
import com.reservamentor.dto.PurchaseReportDTO;

import java.util.List;

public interface PurchaseService {
    PurchaseDTO createPurchase(PurchaseCreateUpdateDTO purchaseDTO);
    //List<PurchaseDTO> getPurchaseHistoryByUserId(Integer userId);
    List<PurchaseDTO> getPurchaseHistoryByUserId();

    List<PurchaseReportDTO> getPurchaseReportByDate();

    List<PurchaseDTO> getAllPurchases();
    PurchaseDTO confirmPurchase(Integer purchaseId);
    PurchaseDTO getPurchaseById(Integer id);


}