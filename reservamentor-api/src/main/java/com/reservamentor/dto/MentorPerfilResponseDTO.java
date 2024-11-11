package com.reservamentor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MentorPerfilResponseDTO {
    private String nombre;
    private String fotoPerfil;
    private String descripcion;
    private Double calificacion;
    private Double tarifaPorHora;
}