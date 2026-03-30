package com.DM.AcademicERP.repository;

import com.DM.AcademicERP.entity.CumulativeResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CumulativeResultRepository extends JpaRepository<CumulativeResult, Long> {
}
