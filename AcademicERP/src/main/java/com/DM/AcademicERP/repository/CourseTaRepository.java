package com.DM.AcademicERP.repository;

import com.DM.AcademicERP.entity.CourseTa;
import com.DM.AcademicERP.entity.CourseTaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseTaRepository extends JpaRepository<CourseTa, CourseTaId> {
    List<CourseTa> findByCourseOffering_OfferingId(Long offeringId);
}
