package com.DM.AcademicERP.repository;

import com.DM.AcademicERP.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCourseOffering_OfferingId(Long offeringId);
    List<Enrollment> findByStudent_StudentId(Long studentId);
}
