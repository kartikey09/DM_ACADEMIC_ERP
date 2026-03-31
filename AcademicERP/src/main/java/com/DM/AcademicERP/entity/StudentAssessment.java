package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student_assessment", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "assessment_id"})
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_assessment_id")
    private Long studentAssessmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;

    @Column(name = "marks_obtained", precision = 6, scale = 2, nullable = false)
    private BigDecimal marksObtained;

    // Default constructor
    public StudentAssessment() {}

    // All-args constructor
    public StudentAssessment(Long studentAssessmentId, Student student, Assessment assessment, 
                           BigDecimal marksObtained) {
        this.studentAssessmentId = studentAssessmentId;
        this.student = student;
        this.assessment = assessment;
        this.marksObtained = marksObtained;
    }

    // Getters and Setters
    public Long getStudentAssessmentId() {
        return studentAssessmentId;
    }

    public void setStudentAssessmentId(Long studentAssessmentId) {
        this.studentAssessmentId = studentAssessmentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public BigDecimal getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(BigDecimal marksObtained) {
        this.marksObtained = marksObtained;
    }
}
