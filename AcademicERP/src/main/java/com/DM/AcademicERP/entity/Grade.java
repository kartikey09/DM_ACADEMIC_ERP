package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "grade")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id", nullable = false, unique = true)
    private Enrollment enrollment;

    @Column(name = "marks_obtained", precision = 5, scale = 2, nullable = false)
    private BigDecimal marksObtained;

    @Column(name = "max_marks", precision = 5, scale = 2, nullable = false)
    private BigDecimal maxMarks;

    @Column(name = "grade_letter", nullable = false, length = 3)
    private String gradeLetter;

    // Default constructor
    public Grade() {}

    // All-args constructor
    public Grade(Long gradeId, Enrollment enrollment, BigDecimal marksObtained, 
                BigDecimal maxMarks, String gradeLetter) {
        this.gradeId = gradeId;
        this.enrollment = enrollment;
        this.marksObtained = marksObtained;
        this.maxMarks = maxMarks;
        this.gradeLetter = gradeLetter;
    }

    // Getters and Setters
    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public BigDecimal getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(BigDecimal marksObtained) {
        this.marksObtained = marksObtained;
    }

    public BigDecimal getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(BigDecimal maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getGradeLetter() {
        return gradeLetter;
    }

    public void setGradeLetter(String gradeLetter) {
        this.gradeLetter = gradeLetter;
    }
}
