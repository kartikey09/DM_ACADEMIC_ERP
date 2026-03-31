package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.Semester;
import com.DM.AcademicERP.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Semester API", description = "Endpoints for managing Semester")
@RestController
@RequestMapping("/api/semesters")
public class SemesterController {

    @Autowired
    private SemesterRepository repository;

    @Operation(summary = "Get all Semesters")
    @GetMapping
    public List<Semester> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Get Semester ('/{id}')")
    @GetMapping("/{id}")
    public ResponseEntity<Semester> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new Semester")
    @PostMapping
    public Semester create(@RequestBody Semester semester) {
        return repository.save(semester);
    }

    @Operation(summary = "Update an existing Semester")
    @PutMapping("/{id}")
    public ResponseEntity<Semester> update(@PathVariable Long id, @RequestBody Semester updated) {
        return repository.findById(id).map(existing -> {
            existing.setTerm(updated.getTerm());
            existing.setYear(updated.getYear());
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a Semester")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
