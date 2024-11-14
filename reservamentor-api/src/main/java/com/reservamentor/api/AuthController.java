package com.reservamentor.api;


import com.reservamentor.dto.AuthResponseDTO;
import com.reservamentor.dto.LoginUsuarioDTO;
import com.reservamentor.dto.PerfilUsuarioDTO;
import com.reservamentor.dto.RegistroUsuarioDTO;
import com.reservamentor.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    @CrossOrigin
    @PostMapping("/register/estudiante")
    public ResponseEntity<PerfilUsuarioDTO> registroEstudiante(@Valid @RequestBody RegistroUsuarioDTO registroUsuarioDTO) {
        PerfilUsuarioDTO perfilUsuario = usuarioService.registrarEstudiante(registroUsuarioDTO);
        return new ResponseEntity<>(perfilUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/register/mentor")
    public ResponseEntity<PerfilUsuarioDTO> registroMentor(@Valid @RequestBody RegistroUsuarioDTO registroUsuarioDTO) {
        PerfilUsuarioDTO perfilUsuario = usuarioService.registrarMentor(registroUsuarioDTO);
        return new ResponseEntity<>(perfilUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginUsuarioDTO loginUsuarioDTO) {
        AuthResponseDTO authResponseDTO = usuarioService.login(loginUsuarioDTO);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }


}
