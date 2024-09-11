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
public class ParticipacionEventoId implements java.io.Serializable {
    private static final long serialVersionUID = -8738558266160597666L;
    @NotNull
    @Column(name = "mentorid", nullable = false)
    private Integer mentorid;

    @NotNull
    @Column(name = "evento_id", nullable = false)
    private Integer eventoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ParticipacionEventoId entity = (ParticipacionEventoId) o;
        return Objects.equals(this.eventoId, entity.eventoId) &&
                Objects.equals(this.mentorid, entity.mentorid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventoId, mentorid);
    }

}