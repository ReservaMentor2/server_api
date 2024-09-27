package com.reservamentor.api;

import com.reservamentor.model.entity.OportunidadLaboral;
import com.reservamentor.service.OportunidadLaboralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadLaboralController {

    private final OportunidadLaboralService oportunidadLaboralService;

    public OportunidadLaboralController(OportunidadLaboralService oportunidadLaboralService) {
        this.oportunidadLaboralService = oportunidadLaboralService;
    }

    @GetMapping
    public ResponseEntity<List<OportunidadLaboral>> getAllOportunidades() {
        List<OportunidadLaboral> oportunidades = oportunidadLaboralService.getAllOportunidades();
        return ResponseEntity.ok(oportunidades);
    }
}
