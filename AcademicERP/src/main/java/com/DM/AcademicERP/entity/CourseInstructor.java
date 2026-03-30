package com.DM.AcademicERP.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "course_instructor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInstructor {

    public enum InstructorRole {
        PRIMARY, CO_INSTRUCTOR, GUEST
    }

    @EmbeddedId
    private CourseInstructorId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("offeringId")
    @JoinColumn(name = "offering_id")
    private CourseOffering courseOffering;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("professorId")
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    @Builder.Default
    private InstructorRole role = InstructorRole.PRIMARY;
}
