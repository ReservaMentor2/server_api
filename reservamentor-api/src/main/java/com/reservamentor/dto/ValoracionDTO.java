package com.reservamentor.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionDTO {

    @NotBlank(message = "El comentario es obligatorio")
    @Size(max = 250, message = "El comentario debe tener 200 caracteres o menos")
    private String comentario;


    @NotNull
    @Column(name = "estrellas", nullable = false)
    private Integer estrellas;
}
