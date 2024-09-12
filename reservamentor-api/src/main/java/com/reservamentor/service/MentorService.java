package com.reservamentor.service;


import com.reservamentor.model.entity.Mentor;

import java.util.List;

public interface MentorService {

    Mentor createMentor(Mentor mentor);
    List<Mentor> getAllMentores();
    Mentor getMentorById(Integer mentorId);
    //Mentor updateMentor(Integer mentorId, Mentor mentor);
}
