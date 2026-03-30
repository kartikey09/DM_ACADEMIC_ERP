package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "program", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"department_id", "name"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program {

    public enum ProgramType {
        B_TECH, M_TECH, IM_TECH, PHD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long programId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProgramType type;

    @Column(name = "duration_years", nullable = false)
    private Integer durationYears;
}
