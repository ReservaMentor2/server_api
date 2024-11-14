package com.reservamentor.dto;

import lombok.Data;

@Data
public class PurchaseItemCreateUpdateDTO {
    private Integer bookId;
    private Integer quantity;
    private Float price;
}