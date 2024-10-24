package com.reservamentor.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "purchase_items")
public class PurchaseItem {
    @Id
    @Column(name ="purchaseid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float price;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "sesionmentoriaid", nullable = false)
    private SesionMentoria sesionMentoriaid;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchase")
    public Purchase purchase;
}