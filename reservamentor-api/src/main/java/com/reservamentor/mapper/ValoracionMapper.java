package com.reservamentor.mapper;

import com.reservamentor.dto.ValoracionDTO;
import com.reservamentor.model.entity.Valoracion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValoracionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ValoracionDTO toDTO(Valoracion valoracion) {
        return modelMapper.map(valoracion, ValoracionDTO.class);
    }

    public Valoracion toEntity(ValoracionDTO valoracionDTO) {
        return modelMapper.map(valoracionDTO, Valoracion.class);
    }
}