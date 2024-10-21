package com.reservamentor.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class SesionMentoriaFeedbackDTO {
    private Integer mentorId;
    private Integer estudianteId;
    private Integer asignaturaId;
    private LocalDate dia;
    private LocalTime horaInicio;
    private LocalTime horaFinal;
    private String weblink;
    private BigDecimal precio;
    private String progreso; 
}
