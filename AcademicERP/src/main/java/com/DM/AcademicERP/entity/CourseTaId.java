package com.DM.AcademicERP.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class CourseTaId implements Serializable {
    private Long studentId;
    private Long offeringId;

    // Default constructor
    public CourseTaId() {}

    // All-args constructor
    public CourseTaId(Long studentId, Long offeringId) {
        this.studentId = studentId;
        this.offeringId = offeringId;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(Long offeringId) {
        this.offeringId = offeringId;
    }

    // equals and hashCode for proper composite key behavior
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseTaId that = (CourseTaId) o;
        return studentId.equals(that.studentId) && offeringId.equals(that.offeringId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(studentId, offeringId);
    }
}
