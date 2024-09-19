package com.reservamentor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InformacionMentorDTO {
    private Integer idMentor;
    private String nombre;
    private String apellido;
    private Integer tarifahora;
    private String biografia;
}
