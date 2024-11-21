package com.reservamentor.mapper;

import com.reservamentor.dto.AsignaturaDelMentorDTO;
import com.reservamentor.dto.DisponibilidadDTO;
import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.exception.MentorNotFound;
import com.reservamentor.exception.ResourceNotFoundException;
import com.reservamentor.model.entity.Mentor;
import java.util.List;

import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.AsignaturaRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MentorMapper {

  private final ModelMapper modelMapper;
  private final MentorRepository mentorRepository;
  private final UsuarioRepository usuarioRepository;
  private final AsignaturaRepository asignaturaRepository;

  public MentorMapper(ModelMapper modelMapper, MentorRepository mentorRepository1, UsuarioRepository usuarioRepository1, AsignaturaRepository asignaturaRepository1) {
    this.modelMapper = modelMapper;
    this.mentorRepository = mentorRepository1;
    this.usuarioRepository = usuarioRepository1;
    this.asignaturaRepository = asignaturaRepository1;
  }

  public MentorPerfilDTO MPtoDTO(Mentor mentor) {
    return modelMapper.map(mentor, MentorPerfilDTO.class);
  }

  public Mentor toEntity(MentorPerfilDTO mentorPerfilDTO) {
    return modelMapper.map(mentorPerfilDTO, Mentor.class);
  }

  public InformacionMentorDTO IMtoDTO(Mentor mentor) {
    Usuario usuario = usuarioRepository.findById(mentor.getUsuarioId().getId()).orElseThrow(
            () -> new ResourceNotFoundException("El usuario no fue encontrado")
    );

    List<AsignaturaDelMentorDTO> asignaturasQueManeja = asignaturaRepository.findAsignaturasByMentor(mentor).stream()
            .map(asignatura -> new AsignaturaDelMentorDTO(asignatura.getId(), asignatura.getNombre()))
            .toList();

    InformacionMentorDTO informacionMentorDTO =
        modelMapper.map(mentor, InformacionMentorDTO.class);
    informacionMentorDTO.setIdMentor(mentor.getId());
    informacionMentorDTO.setTarifahora(mentor.getTarifahora());
    informacionMentorDTO.setNombre(mentor.getUsuarioId().getNombre());
    informacionMentorDTO.setApellido(mentor.getUsuarioId().getApellido());
    informacionMentorDTO.setValoracion(mentor.getValoracionpromedio());
    informacionMentorDTO.setImagePath(usuario.getImagePath());
    informacionMentorDTO.setListaAsignaturas(asignaturasQueManeja);



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