package com.reservamentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadDTO {

    @NotBlank(message = "El día es obligatorio.")
    private String dia;

    @NotNull(message = "La hora de inicio es obligatoria.")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin es obligatoria.")
    private LocalTime horaFin;
}
