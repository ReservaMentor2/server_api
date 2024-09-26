package com.reservamentor.api;

import com.reservamentor.dto.PreferenciasMentorDTO;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.service.RecomendacionMentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentores")
public class RecomendacionMentorController {

    @Autowired
    private RecomendacionMentorService recomendacionMentorService;

    @PostMapping("/recomendaciones")
    public List<Mentor> recomendarMentores(@RequestBody PreferenciasMentorDTO preferencias) {
        return recomendacionMentorService.recomendarMentores(preferencias);
    }
}
