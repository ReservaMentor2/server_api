package com.reservamentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MentorPerfilDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre debe tener 50 caracteres o menos")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 50, message = "El apellido debe tener 50 caracteres o menos")
    private String apellido;

    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 100, message = "El correo debe tener 100 caracteres o menos")
    private String correo;

    @NotBlank(message = "La nacionalidad es obligatoria")
    @Size(max = 50, message = "El correo debe tener 50 caracteres o menos")
    private String nacionalidad;

    @NotBlank(message = "El telefono es obligatorio")
    @Size(max = 250, message = "El telefono debe tener 10 caracteres o menos")
    private String telefono;

    private BigDecimal valoracionPromedio;
    private Integer tarifaHora;

    @NotBlank(message = "La biografia es obligatoria")
    @Size(max = 250, message = "La biografia debe tener 250 caracteres o menos")
    private String biografia;

    public MentorPerfilDTO() {
    }
}