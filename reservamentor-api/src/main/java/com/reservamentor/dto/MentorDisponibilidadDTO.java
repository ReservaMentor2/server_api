package com.reservamentor.dto;

import java.time.LocalTime;

public class MentorDisponibilidadDTO {

    private String dia;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private Integer mentorId;

    private String mentorNombre;

    // Getters and Setters
    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public Integer getMentorId() { return mentorId; }
    public void setMentorId(Integer mentorId) { this.mentorId = mentorId; }

    public String getMentorNombre() { return mentorNombre; }
    public void setMentorNombre(String mentorNombre) { this.mentorNombre = mentorNombre; }
}