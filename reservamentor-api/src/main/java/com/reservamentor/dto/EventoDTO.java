package com.reservamentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoDTO {
    private Integer id;
    @NotBlank(message = "El evento debe tener un titulo")
    @Size(max = 100, message = "El titulo tiene un limite de 100 caracteres")
    private String titulo;
    @Size(max = 500, message = "La descripcion tiene un limite de 500 caracteres")
    private String descripcion;
    @NotBlank(message = "El evento debe tener fecha")
    private LocalDate fecha;
    private Boolean esvirtual = false;
    @Size(max = 200, message = "La ubicacion tiene un limite de 200 caracteres")
    @NotBlank(message = "El evento debe tener una ubicacion virtual o fisica")
    private String ubicacion;
}
