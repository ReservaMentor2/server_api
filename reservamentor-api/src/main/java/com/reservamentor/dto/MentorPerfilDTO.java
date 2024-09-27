package com.reservamentor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MentorPerfilDTO {
    private String nombre;
    private String apellido;
    private String correo;
    private String nacionalidad;
    private String telefono;
    private Double valoracionPromedio;
    private Integer tarifaHora;
    private String biografia;
}