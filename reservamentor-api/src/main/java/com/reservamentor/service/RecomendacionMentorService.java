package com.reservamentor.service;

import com.reservamentor.dto.PreferenciasMentorDTO;
import com.reservamentor.model.entity.Mentor;
import com.reservamentor.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RecomendacionMentorService {

    public List<Mentor> recomendarMentores(PreferenciasMentorDTO preferencias);

}
