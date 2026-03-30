package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "semester_result", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "semester_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Builder.Default
    private Integer backlogs = 0;

    @Column(name = "total_grade_points", precision = 7, scale = 2, nullable = false)
    private BigDecimal totalGradePoints;

    @Enumerated(EnumType.STRING)
    @Column(name = "result_status", nullable = false)
    private ResultStatus resultStatus;
}
