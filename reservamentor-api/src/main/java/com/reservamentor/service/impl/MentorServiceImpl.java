package com.reservamentor.service.impl;

import com.reservamentor.dto.InformacionMentorDTO;
import com.reservamentor.dto.MentorPerfilDTO;
import com.reservamentor.dto.MentorUpdateRequestDTO;
import com.reservamentor.exception.BadRequestException;
import com.reservamentor.exception.MentorNotFound;
import com.reservamentor.mapper.MentorMapper;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.model.entity.Usuario;
import com.reservamentor.repository.DisponibilidadRepository;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorMapper mentorMapper;
    private final MentorRepository mentorRepository;
    private final DisponibilidadRepository disponibilidadRepository;

    @Transactional(readOnly = true)
    public List<InformacionMentorDTO> getAll() {
        List<Mentor> mentors = mentorRepository.findAll();
        return  mentors.stream().map(mentorMapper::IMtoDTO).toList();
    }

    @Transactional
    public Mentor searchByUsuarioId(Usuario usuario) {
        Mentor mentor = mentorRepository.findByUsuarioId(usuario).orElseThrow(
                () -> new MentorNotFound("El mentor no fue encontrado")
        );

        return mentor;
    }

    @Transactional
    public MentorPerfilDTO searchById(Integer id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(
                () -> new MentorNotFound("El mentor con ID " + id + " no fue encontrado")
        );

        return mentorMapper.MPtoDTO(mentor);
    }

    @Transactional(readOnly = true)
    @Override
    public List<InformacionMentorDTO> searchByDia(String dia) {
        if(dia == null || dia.trim().isEmpty()) {
            throw new BadRequestException("El dia no puede estar vacio");
        }

        List<Mentor> mentors = disponibilidadRepository.findMentorsByDia(dia);
        if (mentors.isEmpty()) {
            throw new MentorNotFound("No se encontraron mentores disponibles para el día: " + dia);
        }

        return mentors.stream().map(mentorMapper::IMtoDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<InformacionMentorDTO> searchByHora(LocalTime horaInicio, LocalTime horaFin) {
        if (horaInicio.toString().isEmpty() || horaFin.toString().isEmpty()) {
            throw new BadRequestException("Las horas de inicio y fin no pueden estar vacías.");
        }

        if (horaInicio.isAfter(horaFin)) {
            throw new BadRequestException("La hora de inicio debe ser anterior a la hora de fin.");
        }

        List<Mentor> mentors = disponibilidadRepository.findMentorsByHora(horaInicio, horaFin);

        if (mentors.isEmpty()) {
            throw new MentorNotFound("No se encontraron mentores disponibles entre las horas: " +
                    horaInicio + " y " + horaFin);
        }

        return mentors.stream().map(mentorMapper::IMtoDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<InformacionMentorDTO> searchByDiaAndHora(String dia, LocalTime horaInicio, LocalTime horaFin) {
        if (dia == null || dia.trim().isEmpty()) {
            throw new BadRequestException("El día no puede estar vacío.");
        }

        if (horaInicio.toString().isEmpty() || horaFin.toString().isEmpty()) {
            throw new BadRequestException("Las horas de inicio y fin no pueden estar vacías.");
        }

        if (horaInicio.isAfter(horaFin)) {
            throw new BadRequestException("La hora de inicio debe ser anterior a la hora de fin.");
        }

        List<Mentor> mentors = disponibilidadRepository.findMentorsByDiaAndHora(dia, horaInicio, horaFin);
        if (mentors.isEmpty()) {
            throw new MentorNotFound("No se encontraron mentores disponibles para el día: " + dia +
                    " entre las horas: " + horaInicio + " y " + horaFin);
        }

        return mentors.stream().map(mentorMapper::IMtoDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<InformacionMentorDTO> sortAllByRating() {
        List<Mentor> mentors = mentorRepository.findAllOrderByValoracionpromedio();

        return mentors.stream().map(mentorMapper::IMtoDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<InformacionMentorDTO> getMentorsByPage(Pageable pageable) {
        Page<Mentor> mentors = mentorRepository.findAll(pageable);
        return mentors.map(mentorMapper::IMtoDTO);
    }


    @Override
    public InformacionMentorDTO create(InformacionMentorDTO informacionMentorDTO) {
        Mentor mentor = mentorMapper.toEntity(informacionMentorDTO);

        mentor.setValoracionpromedio(BigDecimal.valueOf(0.0));

        mentor = mentorRepository.save(mentor);
        return mentorMapper.IMtoDTO(mentor);
    }

    @Transactional
    @Override
    public InformacionMentorDTO update(MentorUpdateRequestDTO updateMentor, Integer id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(
                () -> new MentorNotFound("El mentor con ID " + id + " no fue encontrado")
        );
        mentor.setBiografia(updateMentor.getBiografia());
        mentor.setTarifahora(updateMentor.getTarifaHora());

        mentor = mentorRepository.save(mentor);
        return mentorMapper.IMtoDTO(mentor);
    }

    @Transactional
    public void delete(Integer id) {
        Mentor mentor = mentorRepository.findById(id).orElseThrow(
                () -> new MentorNotFound("El mentor con ID " + id + " no fue encontrado")
        );
        mentorRepository.delete(mentor);
    }

}
