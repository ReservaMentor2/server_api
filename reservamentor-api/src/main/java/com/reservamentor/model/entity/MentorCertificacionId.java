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
public class MentorCertificacionId implements java.io.Serializable {
    private static final long serialVersionUID = -9184476791412633847L;
    @NotNull
    @Column(name = "mentorid", nullable = false)
    private Integer mentorid;

    @NotNull
    @Column(name = "certificacionid", nullable = false)
    private Integer certificacionid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MentorCertificacionId entity = (MentorCertificacionId) o;
        return Objects.equals(this.certificacionid, entity.certificacionid) &&
                Objects.equals(this.mentorid, entity.mentorid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(certificacionid, mentorid);
    }

}