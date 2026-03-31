package com.DM.AcademicERP.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "professor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "designation", nullable = false, length = 100)
    private String designation;

    // Default constructor
    public Professor() {}

    // All-args constructor
    public Professor(Long professorId, String name, String email, String designation) {
        this.professorId = professorId;
        this.name = name;
        this.email = email;
        this.designation = designation;
    }

    // Getters and Setters
    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
