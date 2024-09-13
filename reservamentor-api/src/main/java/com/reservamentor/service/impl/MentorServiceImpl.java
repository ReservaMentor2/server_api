package com.reservamentor.service.impl;

import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.MentorRepository;
import com.reservamentor.service.MentorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;

    @Override
    public Mentor createMentor(Mentor mentor) {
        //TODO ADD
        return mentorRepository.save(mentor);
    }

    @Override
    public List<Mentor> getAllMentores() {
        return mentorRepository.findAll();
    }

    @Override
    public Mentor getMentorById(Integer mentorId) {
        return mentorRepository.findById(mentorId)
                .orElseThrow(() -> new RuntimeException("Mentor not found"));
    }

    //@Override
    //@Transactional
    //public Mentor updateMentor(Integer mentorId, Mentor mentor) {
        //TODO
        //ACCESS THE USER TABLE
    //}

}
