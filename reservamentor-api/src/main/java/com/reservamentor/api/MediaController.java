package com.reservamentor.api;

import com.reservamentor.dto.UploadMediaDTO;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.UsuarioRepository;
import com.reservamentor.service.MentorService;
import com.reservamentor.service.StorageService;
import com.reservamentor.service.UsuarioService;
import lombok.AllArgsConstructor;
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
@RequestMapping("/image")
@RestController
@PreAuthorize("hasAnyRole('ESTUDIANTE', 'ADMIN','MENTOR')")
public class MediaController {

    private final StorageService storageService;

    @GetMapping("/{path}")
    public ResponseEntity<Resource> getImage(@PathVariable String path) throws IOException {
        Resource resource = storageService.loadAsResource(path);
        String contentType = Files.probeContentType(resource.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(resource);
    }
}
