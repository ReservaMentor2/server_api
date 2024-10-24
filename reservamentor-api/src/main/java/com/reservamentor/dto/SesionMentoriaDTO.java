package com.reservamentor.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SesionMentoriaDTO {
    @NotNull
    private Integer id;
    @NotNull
    private LocalDate dia;
    @NotNull
    private LocalTime horainicio;
    @NotNull
    private LocalTime horafinal;
    @NotNull
    @Size(max = 200)
    private String weblink;

    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}