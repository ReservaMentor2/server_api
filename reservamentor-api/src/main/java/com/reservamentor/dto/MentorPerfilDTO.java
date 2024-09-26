package com.reservamentor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MentorPerfilDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String nacionalidad;
    private String telefono;
    private BigDecimal valoracionPromedio;
    private Integer tarifaHora;
    private String biografia;
}