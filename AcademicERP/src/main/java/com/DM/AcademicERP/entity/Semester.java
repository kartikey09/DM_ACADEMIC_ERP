package com.DM.AcademicERP.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "semester", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"term", "year"})
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Semester {

    public enum Term {
        ODD, EVEN, SUMMER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id")
    private Long semesterId;

    @Enumerated(EnumType.STRING)
    @Column(name = "term", nullable = false)
    private Term term;

    @Column(name = "year", nullable = false)
    private Integer year;

    // Default constructor
    public Semester() {}

    // All-args constructor
    public Semester(Long semesterId, Term term, Integer year) {
        this.semesterId = semesterId;
        this.term = term;
        this.year = year;
    }

    // Getters and Setters
    public Long getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Long semesterId) {
        this.semesterId = semesterId;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
