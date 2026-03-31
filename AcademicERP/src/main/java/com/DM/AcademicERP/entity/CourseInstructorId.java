package com.DM.AcademicERP.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class CourseInstructorId implements Serializable {
    private Long offeringId;
    private Long professorId;

    // Default constructor
    public CourseInstructorId() {}

    // All-args constructor
    public CourseInstructorId(Long offeringId, Long professorId) {
        this.offeringId = offeringId;
        this.professorId = professorId;
    }

    // Getters and Setters
    public Long getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(Long offeringId) {
        this.offeringId = offeringId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    // equals and hashCode for proper composite key behavior
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseInstructorId that = (CourseInstructorId) o;
        return offeringId.equals(that.offeringId) && professorId.equals(that.professorId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(offeringId, professorId);
    }
}
