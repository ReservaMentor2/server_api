package com.reservamentor.service;

import com.reservamentor.dto.AuthResponseDTO;
import com.reservamentor.dto.LoginUsuarioDTO;
import com.reservamentor.dto.PerfilUsuarioDTO;
import com.reservamentor.dto.RegistroUsuarioDTO;
import com.reservamentor.model.entity.Usuario;

public interface UsuarioService {

    //Registro de usuario
    PerfilUsuarioDTO registrarEstudiante(RegistroUsuarioDTO registroUsuarioDTO);

    //Registro de mentor
    PerfilUsuarioDTO registrarMentor(RegistroUsuarioDTO registroUsuarioDTO);

    // Ingreso sesion de usuario
    AuthResponseDTO login(LoginUsuarioDTO loginUsuarioDTO);

    // Actualizar el perfil del usuario
    PerfilUsuarioDTO actualizarPerfil(Integer id, PerfilUsuarioDTO perfilUsuarioDTO);

    //Obtener el perfil del usuario por ID
    PerfilUsuarioDTO getPerfilById(Integer id);

    //Obtiene el usuario
    Usuario getUsuario(String token);

}
