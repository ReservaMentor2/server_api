package com.reservamentor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionmentoriaDTO {
    @NotNull
    private Integer id;
    @NotNull
    private LocalDate dia;
    @NotNull
    private LocalTime horainicio;
    @NotNull
    private LocalTime horafinal;
    @NotNull
    @Size(max = 200)
    private String weblink;
}