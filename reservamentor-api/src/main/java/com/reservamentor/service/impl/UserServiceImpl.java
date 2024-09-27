package com.reservamentor.service.impl;

import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.UsuarioRepository;
import com.reservamentor.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public Usuario registerUsuario(Usuario usuario) {
        if(usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya existe");
        }

        //TODO ADD ROL MENTOR O USUARIO AN CREATE THE TABLE

        return usuarioRepository.save(usuario);
    }

}
