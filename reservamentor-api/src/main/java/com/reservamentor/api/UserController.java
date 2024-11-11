package com.reservamentor.api;

import com.reservamentor.dto.EstudiantePerfilResponseDTO;
import com.reservamentor.dto.MentorPerfilResponseDTO;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UserController {

    private final UsuarioService usuarioService;

    @GetMapping("/perfil")
    public Usuario getPerfilUsuario(@RequestHeader("Authorization") String token) {
        return usuarioService.getPerfilUsuario(token);
    }
}