package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "assessment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Assessment {

    public enum AssessmentType {
        QUIZ, MIDSEM, ENDSEM, ASSIGNMENT, PROJECT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assessment_id")
    private Long assessmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_id", nullable = false)
    private CourseOffering courseOffering;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AssessmentType type;

    @Column(name = "max_marks", precision = 6, scale = 2, nullable = false)
    private BigDecimal maxMarks;

    @Column(name = "weightage", precision = 5, scale = 2, nullable = false)
    private BigDecimal weightage;

    @Column(name = "assessment_date", nullable = false)
    private LocalDate assessmentDate;

    // Default constructor
    public Assessment() {}

    // All-args constructor
    public Assessment(Long assessmentId, CourseOffering courseOffering, AssessmentType type, 
                     BigDecimal maxMarks, BigDecimal weightage, LocalDate assessmentDate) {
        this.assessmentId = assessmentId;
        this.courseOffering = courseOffering;
        this.type = type;
        this.maxMarks = maxMarks;
        this.weightage = weightage;
        this.assessmentDate = assessmentDate;
    }

    // Getters and Setters
    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public CourseOffering getCourseOffering() {
        return courseOffering;
    }

    public void setCourseOffering(CourseOffering courseOffering) {
        this.courseOffering = courseOffering;
    }

    public AssessmentType getType() {
        return type;
    }

    public void setType(AssessmentType type) {
        this.type = type;
    }

    public BigDecimal getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(BigDecimal maxMarks) {
        this.maxMarks = maxMarks;
    }

    public BigDecimal getWeightage() {
        return weightage;
    }

    public void setWeightage(BigDecimal weightage) {
        this.weightage = weightage;
    }

    public LocalDate getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(LocalDate assessmentDate) {
        this.assessmentDate = assessmentDate;
    }
}
