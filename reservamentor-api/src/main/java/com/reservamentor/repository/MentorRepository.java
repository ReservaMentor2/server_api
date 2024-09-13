package com.reservamentor.repository;

import com.reservamentor.model.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer> {

    List<Mentor> findMentorById(Integer mentorId);

}
