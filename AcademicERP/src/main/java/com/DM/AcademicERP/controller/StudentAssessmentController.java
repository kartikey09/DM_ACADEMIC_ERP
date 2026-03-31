package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.StudentAssessment;
import com.DM.AcademicERP.repository.StudentAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "StudentAssessment API", description = "Endpoints for managing StudentAssessment")
@RestController
@RequestMapping("/api/assessments/{assessmentId}/marks")
public class StudentAssessmentController {

    @Autowired
    private StudentAssessmentRepository repository;

    @Operation(summary = "Get all StudentAssessments")
    @GetMapping
    public List<StudentAssessment> getMarksByAssessment(@PathVariable Long assessmentId) {
        return repository.findByAssessment_AssessmentId(assessmentId);
    }

    @Operation(summary = "Create a new StudentAssessment")
    @PostMapping
    public StudentAssessment recordMark(@PathVariable Long assessmentId, @RequestBody StudentAssessment mark) {
        return repository.save(mark);
    }

    @Operation(summary = "Delete a StudentAssessment")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable Long assessmentId, @PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
