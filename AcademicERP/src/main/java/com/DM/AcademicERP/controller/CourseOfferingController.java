package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.CourseOffering;
import com.DM.AcademicERP.repository.CourseOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CourseOffering API", description = "Endpoints for managing CourseOffering")
@RestController
@RequestMapping("/api/offerings")
public class CourseOfferingController {

    @Autowired
    private CourseOfferingRepository repository;

    @Operation(summary = "Get all CourseOfferings")
    @GetMapping
    public List<CourseOffering> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Get CourseOffering ('/{id}')")
    @GetMapping("/{id}")
    public ResponseEntity<CourseOffering> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new CourseOffering")
    @PostMapping
    public CourseOffering create(@RequestBody CourseOffering offering) {
        return repository.save(offering);
    }

    @Operation(summary = "Delete a CourseOffering")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
