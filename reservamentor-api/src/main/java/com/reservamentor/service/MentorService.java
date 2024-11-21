package com.reservamentor.service;

import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.dto.MentorDetallesDTO;
import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.dto.MentorUpdateRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;


public interface MentorService {

    public List<InformacionMentorDTO> getAll();
    public MentorPerfilDTO searchById(Integer id);
    public List<InformacionMentorDTO> searchByDia(String dia);
    public List<InformacionMentorDTO> searchByHora(LocalTime horaInicio, LocalTime horaFin);
    public List<InformacionMentorDTO> searchByDiaAndHora(String dia, LocalTime horaInicio, LocalTime horaFin);
    public MentorDetallesDTO obtenerDetallesMentor(Integer mentorId);
    public List<InformacionMentorDTO> sortAllByRating();

    public Page<InformacionMentorDTO> getMentorsByPage(Pageable pageable);

    public InformacionMentorDTO create(InformacionMentorDTO informacionMentorDTO);
    public InformacionMentorDTO update(MentorUpdateRequestDTO updateMentor, Integer id);
    public void delete(Integer id);



}
