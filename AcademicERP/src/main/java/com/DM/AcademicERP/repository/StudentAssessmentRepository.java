package com.DM.AcademicERP.repository;

import com.DM.AcademicERP.entity.StudentAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAssessmentRepository extends JpaRepository<StudentAssessment, Long> {
    List<StudentAssessment> findByAssessment_AssessmentId(Long assessmentId);
}
