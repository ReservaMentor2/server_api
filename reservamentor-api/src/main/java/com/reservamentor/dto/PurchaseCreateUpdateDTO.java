package com.reservamentor.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseCreateUpdateDTO {
    private Float total;
    private Integer customerId;
    private List<PurchaseItemCreateUpdateDTO> items;
}