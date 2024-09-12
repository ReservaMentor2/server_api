package com.reservamentor.service;

import com.reservamentor.model.entity.Mentor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;

public interface AdminMentorService {
    List<Mentor> getAll();
    Page<Mentor> paginate(Pageable pageable);
    Mentor create(Mentor mentor);
    Mentor update(Integer id, Mentor mentor);
    void delete(Integer id);
    Mentor findById(Integer id);
    List<Mentor> getAllSortedByRating();
}