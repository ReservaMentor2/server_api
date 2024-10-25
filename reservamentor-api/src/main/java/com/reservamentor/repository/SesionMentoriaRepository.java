package com.reservamentor.repository;


import com.reservamentor.model.entity.SesionMentoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface SesionMentoriaRepository extends JpaRepository<SesionMentoria, Integer> {

    Optional<SesionMentoria> findByTituloOrSlug(String titulo, String slug);

    List<SesionMentoria> findTop6ByOrderByCreatedAtDesc();

}
