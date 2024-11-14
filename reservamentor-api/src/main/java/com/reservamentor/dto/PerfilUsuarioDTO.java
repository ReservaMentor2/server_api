package com.reservamentor.dto;

import com.reservamentor.model.entity.enums.ERol;
import lombok.Data;

@Data
public class PerfilUsuarioDTO {

    private Integer id;

    private String correo;
    private ERol rol;

    private String nombre;
    private String apellido;

    private String nacionalidad;

    private String imagePath;
}
