package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cumulative_result")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CumulativeResult {

    @Id
    private Long studentId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "cgpa", precision = 4, scale = 2, nullable = false)
    private BigDecimal cgpa;

    @Column(name = "total_credits", nullable = false)
    private Integer totalCredits = 0;

    // Default constructor
    public CumulativeResult() {}

    // All-args constructor
    public CumulativeResult(Long studentId, Student student, BigDecimal cgpa, Integer totalCredits) {
        this.studentId = studentId;
        this.student = student;
        this.cgpa = cgpa;
        this.totalCredits = totalCredits;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public BigDecimal getCgpa() {
        return cgpa;
    }

    public void setCgpa(BigDecimal cgpa) {
        this.cgpa = cgpa;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }
}
