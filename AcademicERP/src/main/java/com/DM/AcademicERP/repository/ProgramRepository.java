package com.DM.AcademicERP.repository;

import com.DM.AcademicERP.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByDepartment_DepartmentId(Long departmentId);
}
