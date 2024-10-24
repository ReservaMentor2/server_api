package com.reservamentor.mapper;

import com.reservamentor.dto.SesionMentoriaDTO;
import com.reservamentor.model.entity.SesionMentoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SesionMentoriaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SesionMentoriaDTO toDTO(SesionMentoria sesionmentoria) {
        return modelMapper.map(sesionmentoria, SesionMentoriaDTO.class);
    }

    public SesionMentoria toEntity(SesionMentoriaDTO dto) {
        return modelMapper.map(dto, SesionMentoria.class);
    }



}
