package com.reservamentor.dto;

import com.reservamentor.model.entity.Estudiante;
import com.reservamentor.model.entity.Mentor;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValoracionDTO {
    private Integer id;
    private Integer estudianteId;
    private Integer mentorId;
    private String comentario;
    private Integer estrellas;
    private Boolean valoracionDeMentor;
}