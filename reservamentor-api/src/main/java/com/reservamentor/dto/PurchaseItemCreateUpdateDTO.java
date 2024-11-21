package com.reservamentor.dto;

import lombok.Data;

@Data
public class PurchaseItemCreateUpdateDTO {
    private Integer sesionMentoriaId;
    private Integer quantity;
    private Float price;
}