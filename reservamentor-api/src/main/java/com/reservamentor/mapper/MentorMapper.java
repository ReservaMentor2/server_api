package com.reservamentor.mapper;

import com.reservamentor.dto.DisponibilidadDTO;
import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.model.entity.Mentor;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
    InformacionMentorDTO informacionMentorDTO =
        modelMapper.map(mentor, InformacionMentorDTO.class);
    informacionMentorDTO.setIdMentor(mentor.getId());
    informacionMentorDTO.setTarifahora(mentor.getTarifahora());
    informacionMentorDTO.setNombre(mentor.getUsuarioId().getNombre());
    informacionMentorDTO.setApellido(mentor.getUsuarioId().getApellido());
    informacionMentorDTO.setValoracion(mentor.getValoracionpromedio());

    List<DisponibilidadDTO> disponibilidades =
        mentor.getHorarioDisponible()
            .stream()
            .map(disponibilidad
                 -> modelMapper.map(disponibilidad, DisponibilidadDTO.class))
            .toList();

    informacionMentorDTO.setHorariosDisponibles(disponibilidades);

    return informacionMentorDTO;
  }

  public Mentor toEntity(InformacionMentorDTO informacionMentorDTO) {
    return modelMapper.map(informacionMentorDTO, Mentor.class);
  }
}