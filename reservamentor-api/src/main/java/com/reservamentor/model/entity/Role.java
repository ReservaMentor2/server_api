package com.reservamentor.model.entity;

import com.reservamentor.model.entity.enums.ERol;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private ERol name;

}
