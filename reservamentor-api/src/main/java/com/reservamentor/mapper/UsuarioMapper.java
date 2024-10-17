package com.reservamentor.mapper;


import com.reservamentor.config.ModelMapperConfig;
import com.reservamentor.dto.AuthResponseDTO;
import com.reservamentor.dto.LoginUsuarioDTO;
import com.reservamentor.dto.PerfilUsuarioDTO;
import com.reservamentor.dto.RegistroUsuarioDTO;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UsuarioMapper {

    private final ModelMapperConfig modelMapperConfig;
    private final ModelMapper modelMapper;

    //From LoginDTO to User
    public Usuario toUserEntity(LoginUsuarioDTO loginDTO) {
        return modelMapper.map(loginDTO, Usuario.class);
    }

    //From RegistroUsuarioDTO to User
    public Usuario toUserEntity(RegistroUsuarioDTO registroDTO) {
        return modelMapper.map(registroDTO, Usuario.class);
    }

    public PerfilUsuarioDTO toPerfilUsuarioDTO(Usuario usuario) {
        return modelMapper.map(usuario, PerfilUsuarioDTO.class);
    }

    public AuthResponseDTO toAuthResponseDTO(Usuario usuario, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        String firstName = (usuario != null) ? usuario.getNombre() : "Admin";
        String lastName = (usuario != null) ? usuario.getApellido() : "User";

        authResponseDTO.setFirstName(firstName);
        authResponseDTO.setLastName(lastName);

        authResponseDTO.setRole(usuario.getRol().getName().name());

        return authResponseDTO;
    }

}
