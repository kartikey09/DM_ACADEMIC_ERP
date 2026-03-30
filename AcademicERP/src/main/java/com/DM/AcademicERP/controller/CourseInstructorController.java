package com.DM.AcademicERP.controller;

import com.DM.AcademicERP.entity.CourseInstructor;
import com.DM.AcademicERP.entity.CourseInstructorId;
import com.DM.AcademicERP.repository.CourseInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offerings/{offeringId}/instructors")
public class CourseInstructorController {

    @Autowired
    private CourseInstructorRepository repository;

    @GetMapping
    public List<CourseInstructor> getInstructors(@PathVariable Long offeringId) {
        return repository.findByCourseOffering_OfferingId(offeringId);
    }

    @PostMapping
    public CourseInstructor assignInstructor(@PathVariable Long offeringId, @RequestBody CourseInstructor instructor) {
        return repository.save(instructor);
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity<Void> removeInstructor(@PathVariable Long offeringId, @PathVariable Long professorId) {
        CourseInstructorId id = new CourseInstructorId(offeringId, professorId);
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
