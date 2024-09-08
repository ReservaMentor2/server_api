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
public class AsignaturaMentorId implements java.io.Serializable {
    private static final long serialVersionUID = -4739587989400311955L;
    @NotNull
    @Column(name = "asignaturaid", nullable = false)
    private Integer asignaturaid;

    @NotNull
    @Column(name = "mentorid", nullable = false)
    private Integer mentorid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AsignaturaMentorId entity = (AsignaturaMentorId) o;
        return Objects.equals(this.asignaturaid, entity.asignaturaid) &&
                Objects.equals(this.mentorid, entity.mentorid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asignaturaid, mentorid);
    }

}