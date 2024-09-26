package com.reservamentor.mapper;

import com.reservamentor.dto.DisponibilidadDTO;
import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.model.entity.Mentor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MentorMapper {

    private final ModelMapper modelMapper;

    public MentorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MentorPerfilDTO MPtoDTO(Mentor mentor) {
        return modelMapper.map(mentor, MentorPerfilDTO.class);
    }

    public Mentor toEntity(MentorPerfilDTO mentorPerfilDTO) {
        return modelMapper.map(mentorPerfilDTO, Mentor.class);
    }

    public InformacionMentorDTO IMtoDTO(Mentor mentor) {
        InformacionMentorDTO informacionMentorDTO = modelMapper.map(mentor, InformacionMentorDTO.class);
        informacionMentorDTO.setNombre(mentor.getUsuario().getNombre());
        informacionMentorDTO.setApellido(mentor.getUsuario().getApellido());

        List<DisponibilidadDTO> disponibilidades = mentor.getHorarioDisponible().stream()
                .map(disponibilidad -> modelMapper.map(disponibilidad, DisponibilidadDTO.class))
                .toList();

        informacionMentorDTO.setHorariosDisponibles(disponibilidades);

        return informacionMentorDTO;
    }

    public Mentor toEntity(InformacionMentorDTO informacionMentorDTO) {
        return modelMapper.map(informacionMentorDTO, Mentor.class);
    }
}