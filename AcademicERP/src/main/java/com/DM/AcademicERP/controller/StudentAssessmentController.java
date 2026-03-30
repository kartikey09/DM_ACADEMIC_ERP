package com.DM.AcademicERP.controller;

import com.DM.AcademicERP.entity.StudentAssessment;
import com.DM.AcademicERP.repository.StudentAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments/{assessmentId}/marks")
public class StudentAssessmentController {

    @Autowired
    private StudentAssessmentRepository repository;

    @GetMapping
    public List<StudentAssessment> getMarksByAssessment(@PathVariable Long assessmentId) {
        return repository.findByAssessment_AssessmentId(assessmentId);
    }

    @PostMapping
    public StudentAssessment recordMark(@PathVariable Long assessmentId, @RequestBody StudentAssessment mark) {
        return repository.save(mark);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable Long assessmentId, @PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
