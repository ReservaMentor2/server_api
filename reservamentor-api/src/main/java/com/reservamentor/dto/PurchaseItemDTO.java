package com.reservamentor.dto;

import lombok.Data;

@Data
public class PurchaseItemDTO {
    private Integer id;
    private Float price;
    private Integer quantity;
    private String sesionMentoriaTitle;
}