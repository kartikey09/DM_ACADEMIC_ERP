package com.DM.AcademicERP.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseInstructorId implements Serializable {
    private Long offeringId;
    private Long professorId;
}
