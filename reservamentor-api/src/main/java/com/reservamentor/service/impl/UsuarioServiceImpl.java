package com.reservamentor.service.impl;

import com.reservamentor.dto.AuthResponseDTO;
import com.reservamentor.dto.LoginUsuarioDTO;
import com.reservamentor.dto.PerfilUsuarioDTO;
import com.reservamentor.dto.RegistroUsuarioDTO;
import com.reservamentor.mapper.UsuarioMapper;
import com.reservamentor.model.entity.Estudiante;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Role;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.model.entity.enums.ERol;
import com.reservamentor.repository.EstudianteRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.repository.RolRepository;
import com.reservamentor.repository.UsuarioRepository;
import com.reservamentor.security.TokenProvider;
import com.reservamentor.security.UserPrincipal;
import com.reservamentor.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final MentorRepository mentorRepository;
    private final EstudianteRepository estudianteRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    //Registro de usuario
    @Override
    public PerfilUsuarioDTO registrarEstudiante(RegistroUsuarioDTO registroUsuarioDTO) {
        return registroUsuarioConRol(registroUsuarioDTO, ERol.ESTUDIANTE);
    }

    //Registro de mentor
    @Override
    public PerfilUsuarioDTO registrarMentor(RegistroUsuarioDTO registroUsuarioDTO) {
        return registroUsuarioConRol(registroUsuarioDTO, ERol.MENTOR);
    }

    // Actualizar el perfil del usuario
    @Override
    public PerfilUsuarioDTO actualizarPerfil(Integer id, PerfilUsuarioDTO perfilUsuarioDTO) {
        return null;
    }

    @Override
    public AuthResponseDTO login(LoginUsuarioDTO loginUsuarioDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuarioDTO.getEmail(), loginUsuarioDTO.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Usuario usuario = userPrincipal.getUsuario();

        String token = tokenProvider.createAccessToken(authentication);

        AuthResponseDTO authResponseDTO = usuarioMapper.toAuthResponseDTO(usuario, token);

        return authResponseDTO;
    }


    private PerfilUsuarioDTO registroUsuarioConRol(RegistroUsuarioDTO registroUsuarioDTO, ERol rol) {

        //Verifica si existe el correo en la base de datos
        boolean existsByEmail = usuarioRepository.existsByCorreo(registroUsuarioDTO.getCorreo());

        //Si este existe obtiene el id
        if (existsByEmail) {
            Optional<Usuario> usuario = usuarioRepository.findByCorreo(registroUsuarioDTO.getCorreo());
            Integer usuarioId = usuario.get().getId();

            boolean existsAsMentor = mentorRepository.existsById(usuarioId);
            boolean existsAsEstudiante = estudianteRepository.existsById(usuarioId);

            if(existsAsMentor || existsAsEstudiante) {
                throw new IllegalArgumentException("Ya existe un mentor o estudiante con el mismo correo");
            }

        }

        Role role = rolRepository.findByName(rol)
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado"));

        registroUsuarioDTO.setContrasenia(passwordEncoder.encode(registroUsuarioDTO.getContrasenia()));

        Usuario usuario = usuarioMapper.toUserEntity(registroUsuarioDTO);
        usuario.setRol(role);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        if(rol == ERol.MENTOR) {
            Mentor mentor = new Mentor();
            mentor.setUsuarioId(usuarioGuardado);
            mentor.setBiografia(registroUsuarioDTO.getBiografia());
            mentor.setTarifahora(registroUsuarioDTO.getTarifaHora());
            mentor.setValoracionpromedio(BigDecimal.valueOf(0.0));

            Mentor mentorGuardado = mentorRepository.save(mentor);
        }

        else if(rol == ERol.ESTUDIANTE) {
            Estudiante estudiante = new Estudiante();
            estudiante.setUsuarioid(usuarioGuardado);

            Estudiante estudianteGuardado = estudianteRepository.save(estudiante);
        }


        return usuarioMapper.toPerfilUsuarioDTO(usuarioGuardado);
    }

    //Obtener el perfil del usuario por ID
    public PerfilUsuarioDTO getPerfilById(Integer id) {
        return null;
    }

}
