package com.DM.AcademicERP.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "course_ta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CourseTa {

    public enum TaRole {
        HEAD_TA, TA
    }

    @EmbeddedId
    private CourseTaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("offeringId")
    @JoinColumn(name = "offering_id")
    private CourseOffering courseOffering;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private TaRole role = TaRole.TA;

    // Default constructor
    public CourseTa() {}

    // All-args constructor
    public CourseTa(CourseTaId id, Student student, CourseOffering courseOffering, TaRole role) {
        this.id = id;
        this.student = student;
        this.courseOffering = courseOffering;
        this.role = role;
    }

    // Getters and Setters
    public CourseTaId getId() {
        return id;
    }

    public void setId(CourseTaId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public void setCourseOffering(CourseOffering courseOffering) {
        this.courseOffering = courseOffering;
    }

    public TaRole getRole() {
        return role;
    }

    public void setRole(TaRole role) {
        this.role = role;
    }
}
