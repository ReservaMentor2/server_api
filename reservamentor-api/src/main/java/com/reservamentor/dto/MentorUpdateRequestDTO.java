package com.reservamentor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MentorUpdateRequestDTO {

    @NotBlank(message = "La tarifa de hora es obligatoria")
    private Integer tarifaHora;

    @NotBlank(message = "La biografia es obligatoria")
    @Size(max = 250, message = "La biografia debe tener 250 caracteres o menos")
    private String biografia;
}
