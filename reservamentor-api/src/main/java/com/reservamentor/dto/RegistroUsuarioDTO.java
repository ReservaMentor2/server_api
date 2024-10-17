package com.reservamentor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegistroUsuarioDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Email(message = "El correo no es valido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "La constraseña es obligatorio")
    private String contrasenia;

    private String nacionalidad;
    private Integer telefono;

    //Opcionales
    //Funcionalidades para el mentor
    private String biografia;
    private Integer tarifaHora;

}
