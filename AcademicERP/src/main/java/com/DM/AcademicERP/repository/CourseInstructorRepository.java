package com.DM.AcademicERP.repository;

import com.DM.AcademicERP.entity.CourseInstructor;
import com.DM.AcademicERP.entity.CourseInstructorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseInstructorRepository extends JpaRepository<CourseInstructor, CourseInstructorId> {
    List<CourseInstructor> findByCourseOffering_OfferingId(Long offeringId);
}
