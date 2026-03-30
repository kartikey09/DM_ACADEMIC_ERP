package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "semester", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"term", "year"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
