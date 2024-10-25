package com.reservamentor.api;

import com.reservamentor.dto.PreferenciasMentorDTO;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.service.RecomendacionMentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recomendaciones")
@PreAuthorize("hasAnyRole('ESTUDIANTE', 'ADMIN')")
public class RecomendacionMentorController {

    @Autowired
    private RecomendacionMentorService recomendacionMentorService;

    @PostMapping("/mentor")
    public List<Mentor> recomendarMentores(@RequestBody PreferenciasMentorDTO preferencias) {
        return recomendacionMentorService.recomendarMentores(preferencias);
    }
}
