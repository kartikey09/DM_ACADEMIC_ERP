package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.DM.AcademicERP.entity.CourseInstructor;
import com.DM.AcademicERP.entity.CourseInstructorId;
import com.DM.AcademicERP.repository.CourseInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CourseInstructor API", description = "Endpoints for managing CourseInstructor")
@RestController
@RequestMapping("/api/offerings/{offeringId}/instructors")
public class CourseInstructorController {

    @Autowired
    private CourseInstructorRepository repository;

    @Operation(summary = "Get all CourseInstructors")
    @GetMapping
    public List<CourseInstructor> getInstructors(@PathVariable Long offeringId) {
        return repository.findByCourseOffering_OfferingId(offeringId);
    }

    @Operation(summary = "Create a new CourseInstructor")
    @PostMapping
    public CourseInstructor assignInstructor(@PathVariable Long offeringId, @RequestBody CourseInstructor instructor) {
        return repository.save(instructor);
    }

    @Operation(summary = "Delete a CourseInstructor")
    @DeleteMapping("/{professorId}")
    public ResponseEntity<Void> removeInstructor(@PathVariable Long offeringId, @PathVariable Long professorId) {
        CourseInstructorId id = new CourseInstructorId(offeringId, professorId);
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
