package com.reservamentor.dto;

import java.time.LocalTime;

public class PreferenciasMentorDTO {
    private String especialidad;
    private LocalTime horaInicioPreferida;
    private LocalTime horaFinPreferida;
    private Integer tarifaMaxima;
    private Double valoracionMinima;

    // Getters y Setters
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public LocalTime getHoraInicioPreferida() { return horaInicioPreferida; }
    public void setHoraInicioPreferida(LocalTime horaInicioPreferida) { this.horaInicioPreferida = horaInicioPreferida; }

    public LocalTime getHoraFinPreferida() { return horaFinPreferida; }
    public void setHoraFinPreferida(LocalTime horaFinPreferida) { this.horaFinPreferida = horaFinPreferida; }

    public Integer getTarifaMaxima() { return tarifaMaxima; }
    public void setTarifaMaxima(Integer tarifaMaxima) { this.tarifaMaxima = tarifaMaxima; }

    public Double getValoracionMinima() { return valoracionMinima; }
    public void setValoracionMinima(Double valoracionMinima) { this.valoracionMinima = valoracionMinima; }
}