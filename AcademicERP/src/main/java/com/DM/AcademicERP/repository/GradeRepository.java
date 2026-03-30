package com.DM.AcademicERP.repository;

import com.DM.AcademicERP.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Optional<Grade> findByEnrollment_EnrollmentId(Long enrollmentId);
}
