package com.reservamentor.service.impl;

import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.service.AdminMentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AdminMentorServiceImpl implements AdminMentorService {
    @Autowired
    private MentorRepository mentorRepository;


    @Transactional(readOnly = true)
    @Override
    public List<Mentor> getAll() {
        return mentorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Mentor> paginate(Pageable pageable) {
        return mentorRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Mentor create(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @Transactional
    @Override
    public Mentor update(Integer id, Mentor updateMentor) {
        Mentor mentorFromDb = findById(id);
        mentorFromDb.setHorarioDisponible(updateMentor.getHorarioDisponible());
        return mentorRepository.save(mentorFromDb);
    }

    @Transactional(readOnly = true)
    @Override
    public Mentor findById(Integer id) {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Mentor mentorFromDb = findById(id);
        mentorRepository.delete(mentorFromDb);

    }

    @Transactional(readOnly = true)
    @Override
    public List<Mentor> getAllSortedByRating() {
        return mentorRepository.findAllOrderByValoracionpromedio();
    }

}