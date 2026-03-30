package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_ta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseTa {

    public enum TaRole {
        HEAD_TA, TA
    }

    @EmbeddedId
    private CourseTaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("offeringId")
    @JoinColumn(name = "offering_id")
    private CourseOffering courseOffering;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @Builder.Default
    private TaRole role = TaRole.TA;
}
