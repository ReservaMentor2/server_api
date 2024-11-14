package com.reservamentor.repository;

import com.reservamentor.model.entity.Role;
import com.reservamentor.model.entity.enums.ERol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERol name);

}
