package com.reservamentor.dto;

import com.reservamentor.model.entity.Disponibilidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacionMentorDTO {
  private Integer idMentor;

  @NotBlank(message = "El nombre es obligatorio")
  @Size(max = 50, message = "El nombre debe tener 50 caracteres o menos")
  private String nombre;

  @NotBlank(message = "El apellido es obligatorio")
  @Size(max = 50, message = "El apellido debe tener 50 caracteres o menos")
  private String apellido;

  @NotBlank(message = "La tarifa es obligatoria") private Integer tarifahora;

  @NotBlank(message = "La biografia es obligatoria")
  @Size(max = 250, message = "La biografia debe tener 250 caracteres o menos")
  private String biografia;

  private BigDecimal valoracion;

  private List<DisponibilidadDTO> horariosDisponibles;
  private String ImagePath;
}