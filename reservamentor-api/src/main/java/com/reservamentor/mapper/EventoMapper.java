package com.reservamentor.mapper;

import com.reservamentor.dto.EventoDTO;
import com.reservamentor.model.entity.Evento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {
    private final ModelMapper modelMapper;

    public EventoMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public EventoDTO toDTO(Evento evento){
        return modelMapper.map(evento, EventoDTO.class);
    }

    public Evento toEntity(EventoDTO eventoDTO){
        return modelMapper.map(eventoDTO, Evento.class);
    }
}