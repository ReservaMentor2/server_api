package com.reservamentor.security;

import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(email).orElseThrow(
                () -> new ResourceNotFoundException("Usuario no encontrado con el email: " + email)
        );

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getName().name());

        return new UserPrincipal(
                usuario.getId().intValue(),  // Cambio de getUsuarioId() a getId()
                usuario.getCorreo(),
                usuario.getContrasenia(),
                Collections.singletonList(authority),
                usuario
        );
    }
}
