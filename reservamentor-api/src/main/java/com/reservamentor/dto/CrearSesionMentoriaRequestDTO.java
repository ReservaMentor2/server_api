package com.reservamentor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Getter
@Setter
public class CrearSesionMentoriaRequestDTO {

    @NotNull
    private Integer idMentor;

    @NotNull
    private Integer idEstudiante;// Mediante el token

    @NotNull
    @Size(max = 100)
    private String tituloDelCurso;// F

    @NotNull
    @Size(max = 100)
    private String tituloDeSesionMentoria;// F

    @NotNull
    private LocalTime horaInicio; // F

    @NotNull
    private LocalTime horaFin; // F

    @NotNull
    private LocalDate dia; // F

}
