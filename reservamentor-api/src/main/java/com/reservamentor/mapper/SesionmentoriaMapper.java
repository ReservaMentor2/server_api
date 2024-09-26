package com.reservamentor.mapper;

import com.reservamentor.dto.SesionmentoriaDTO;
import com.reservamentor.model.entity.Sesionmentoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SesionmentoriaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SesionmentoriaDTO toDTO(Sesionmentoria sesionmentoria) {
        return modelMapper.map(sesionmentoria, SesionmentoriaDTO.class);
    }

    public Sesionmentoria toEntity(SesionmentoriaDTO dto) {
        return modelMapper.map(dto, Sesionmentoria.class);
    }
}
