package com.DM.AcademicERP.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseTaId implements Serializable {
    private Long studentId;
    private Long offeringId;
}
