package com.reservamentor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class MentorDetallesDTO {
    private String nombre;
    private String apellido;
    private List<DisponibilidadDTO> horariosDisponibles;
    private String imagePath;
    private Integer tarifaHora;
    private BigDecimal valoracion;
    private List<AsignaturaDelMentorDTO> asignaturas;
}
