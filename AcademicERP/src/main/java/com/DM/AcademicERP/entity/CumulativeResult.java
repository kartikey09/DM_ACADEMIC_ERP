package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cumulative_result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Builder.Default
    private Integer totalCredits = 0;
}
