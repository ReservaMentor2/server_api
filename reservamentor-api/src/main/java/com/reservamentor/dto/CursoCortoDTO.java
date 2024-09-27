package com.reservamentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CursoCortoDTO {

    @NotBlank(message = "El nombre del curso es obligatorio")
    @Size(max = 50, message = "El nombre del curso debe tener 50 caracteres o menos")
    private String nombreCurso;

    @NotBlank(message = "La descripcion del curso es obligatoria")
    @Size(max = 250, message = "La descripcion del curso debe tener 250 caracteres o menos")
    private String descripcionCurso;

    private MentorDisponibilidadDTO disponibilidad;

    // Getters and Setters
    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }

    public String getDescripcionCurso() { return descripcionCurso; }
    public void setDescripcionCurso(String descripcionCurso) { this.descripcionCurso = descripcionCurso; }

    public MentorDisponibilidadDTO getDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(MentorDisponibilidadDTO disponibilidad) { this.disponibilidad = disponibilidad; }
}
