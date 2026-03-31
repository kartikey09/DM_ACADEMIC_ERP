package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "semester_result", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "semester_id"})
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SemesterResult {

    public enum ResultStatus {
        PASS, FAIL, WITHHELD, INCOMPLETE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @Column(name = "semester_gpa", precision = 4, scale = 2, nullable = false)
    private BigDecimal semesterGpa;

    @Column(name = "total_credits", nullable = false)
    private Integer totalCredits;

    @Column(name = "credits_earned", nullable = false)
    private Integer creditsEarned;

    @Column(name = "backlogs", nullable = false)
    private Integer backlogs = 0;

    @Column(name = "total_grade_points", precision = 7, scale = 2, nullable = false)
    private BigDecimal totalGradePoints;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_status", nullable = false)
    private ResultStatus resultStatus;

    // Default constructor
    public SemesterResult() {}

    // All-args constructor
    public SemesterResult(Long resultId, Student student, Semester semester, 
                        BigDecimal semesterGpa, Integer totalCredits, Integer creditsEarned, 
                        Integer backlogs, BigDecimal totalGradePoints, ResultStatus resultStatus) {
        this.resultId = resultId;
        this.student = student;
        this.semester = semester;
        this.semesterGpa = semesterGpa;
        this.totalCredits = totalCredits;
        this.creditsEarned = creditsEarned;
        this.backlogs = backlogs;
        this.totalGradePoints = totalGradePoints;
        this.resultStatus = resultStatus;
    }

    // Getters and Setters
    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public BigDecimal getSemesterGpa() {
        return semesterGpa;
    }

    public void setSemesterGpa(BigDecimal semesterGpa) {
        this.semesterGpa = semesterGpa;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Integer getCreditsEarned() {
        return creditsEarned;
    }

    public void setCreditsEarned(Integer creditsEarned) {
        this.creditsEarned = creditsEarned;
    }

    public Integer getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(Integer backlogs) {
        this.backlogs = backlogs;
    }

    public BigDecimal getTotalGradePoints() {
        return totalGradePoints;
    }

    public void setTotalGradePoints(BigDecimal totalGradePoints) {
        this.totalGradePoints = totalGradePoints;
    }

    public ResultStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResultStatus resultStatus) {
        this.resultStatus = resultStatus;
    }
}
