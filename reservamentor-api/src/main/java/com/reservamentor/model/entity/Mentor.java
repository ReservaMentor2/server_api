package com.reservamentor.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mentor")
public class Mentor {
  @Id @Column(name = "mentorid", nullable = false) private Integer id;
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  // Cambia a EAGER para cargar usuario automáticamente
  @JoinColumn(name = "usuarioid", nullable = false)
  @JsonIgnore
  private Usuario usuarioId;

  @NotNull
  @Column(name = "valoracionpromedio", nullable = false, precision = 2,
          scale = 2)
  private BigDecimal valoracionpromedio;
  @NotNull
  @Column(name = "tarifahora", nullable = false)
  private Integer tarifahora;
  @Size(max = 500)
  @NotNull
  @Column(name = "biografia", nullable = false, length = 500)
  private String biografia;
  @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL,
             orphanRemoval = true)
  @JsonIgnore
  private List<Disponibilidad> horarioDisponible;
}