package com.reservamentor.mapper;

import com.reservamentor.dto.ValoracionDTO;
import com.reservamentor.model.entity.Valoracion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ValoracionMapper {
        private final ModelMapper modelMapper;

        private ValoracionMapper(ModelMapper modelMapper) {
            this.modelMapper = modelMapper;
        }

        public ValoracionDTO toDTO(Valoracion valoracion) {
            return modelMapper.map(valoracion, ValoracionDTO.class);
        }

        public Valoracion toEntity(ValoracionDTO valoracionDTO) {
            return modelMapper.map(valoracionDTO, Valoracion.class);
        }

}
