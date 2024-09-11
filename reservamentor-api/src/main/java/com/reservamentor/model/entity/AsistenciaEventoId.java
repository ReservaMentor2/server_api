package com.reservamentor.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class AsistenciaEventoId implements java.io.Serializable {
    private static final long serialVersionUID = -57790888292228175L;
    @NotNull
    @Column(name = "mentorid", nullable = false)
    private Integer mentorid;

    @NotNull
    @Column(name = "eventoid", nullable = false)
    private Integer eventoid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AsistenciaEventoId entity = (AsistenciaEventoId) o;
        return Objects.equals(this.eventoid, entity.eventoid) &&
                Objects.equals(this.mentorid, entity.mentorid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoid, mentorid);
    }

}