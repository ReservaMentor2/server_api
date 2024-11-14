package com.reservamentor.api;

import com.reservamentor.dto.PerfilUsuarioDTO;
import com.reservamentor.model.entity.SesionMentoria;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.UsuarioRepository;
import com.reservamentor.service.StorageService;
import com.reservamentor.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
@PreAuthorize("hasAnyRole('ESTUDIANTE', 'ADMIN','MENTOR')")
public class UserController {

    private final StorageService storageService;
    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    //Sube la imagen de perfil del usuario
    @PutMapping("/image")
    public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile multipartFile, @RequestHeader("Authorization") String bearerToken) {
        Usuario usuario = usuarioService.getUsuario(bearerToken);
        String path = storageService.store(multipartFile);

        usuario.setImagePath(path);
        usuarioRepository.save(usuario);
        System.out.println(path);

        Usuario usuario1 = usuarioService.getUsuario(bearerToken);
        System.out.println(usuario1.getCorreo());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Retorna solo la imagen de perfil del usuario
    @GetMapping("/image")
    public ResponseEntity<Resource> getProfilePicture(@RequestHeader("Authorization") String bearerToken) throws IOException {
        Usuario usuario = usuarioService.getUsuario(bearerToken);

        String filename = usuario.getImagePath();
        System.out.println(filename);

        Resource resource = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }

    //El perfil del usuario ingresado
    @GetMapping("/")
    public ResponseEntity<PerfilUsuarioDTO> getProfile(@RequestHeader("Authorization") String bearerToken) throws IOException {
        Usuario usuario = usuarioService.getUsuario(bearerToken);
        PerfilUsuarioDTO perfilUsuarioDTO = new PerfilUsuarioDTO();

        perfilUsuarioDTO.setId(usuario.getId());
        perfilUsuarioDTO.setCorreo(usuario.getCorreo());
        perfilUsuarioDTO.setNombre(usuario.getNombre());
        perfilUsuarioDTO.setApellido(usuario.getApellido());
        perfilUsuarioDTO.setImagePath(usuario.getImagePath());

        return new ResponseEntity<PerfilUsuarioDTO>(perfilUsuarioDTO, HttpStatus.OK);
    }



}
