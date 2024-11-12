package com.reservamentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisponibilidadDTO {

    @NotBlank(message = "El día es obligatorio.")
    private LocalDate dia;

    @NotNull(message = "La hora de inicio es obligatoria.")
    private LocalTime horainicio;

    @NotNull(message = "La hora de fin es obligatoria.")
    private LocalTime horafin;
}
