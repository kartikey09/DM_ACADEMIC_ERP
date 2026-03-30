package com.DM.AcademicERP.repository;

import com.DM.AcademicERP.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByCourseOffering_OfferingId(Long offeringId);
}
