package com.reservamentor.dto;

public class CursoCortoDTO {
    private String nombreCurso;
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
