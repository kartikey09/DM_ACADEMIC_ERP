package com.DM.AcademicERP.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "course_instructor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseInstructor {

    public enum InstructorRole {
        PRIMARY, CO_INSTRUCTOR, GUEST
    }

    @EmbeddedId
    private CourseInstructorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("offeringId")
    @JoinColumn(name = "offering_id")
    private CourseOffering courseOffering;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("professorId")
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private InstructorRole role = InstructorRole.PRIMARY;

    // Default constructor
    public CourseInstructor() {}

    // All-args constructor
    public CourseInstructor(CourseInstructorId id, CourseOffering courseOffering, Professor professor, InstructorRole role) {
        this.id = id;
        this.courseOffering = courseOffering;
        this.professor = professor;
        this.role = role;
    }

    // Getters and Setters
    public CourseInstructorId getId() {
        return id;
    }

    public void setId(CourseInstructorId id) {
        this.id = id;
    }

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public void setCourseOffering(CourseOffering courseOffering) {
        this.courseOffering = courseOffering;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public InstructorRole getRole() {
        return role;
    }

    public void setRole(InstructorRole role) {
        this.role = role;
    }
}
