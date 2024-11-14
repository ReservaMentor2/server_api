package com.reservamentor.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@Table(name = "disponibilidad")
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dia", nullable = false)
    private LocalDate dia;

    @Column(name = "horainit", nullable = false)
    private LocalTime horainicio;

    @Column(name = "horafin", nullable = false)
    private LocalTime horafin;

    @ManyToOne
    @JoinColumn(name = "mentorid", referencedColumnName = "mentorid", foreignKey = @ForeignKey(name = "Fk_mentor_disponibilidad"))
    private Mentor mentor;
}