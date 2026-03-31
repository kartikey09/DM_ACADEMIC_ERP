package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.SemesterResult;
import com.DM.AcademicERP.repository.SemesterResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SemesterResult API", description = "Endpoints for managing SemesterResult")
@RestController
@RequestMapping("/api/semester-results")
public class SemesterResultController {

    @Autowired
    private SemesterResultRepository repository;

    @Operation(summary = "Get all SemesterResults")
    @GetMapping
    public List<SemesterResult> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new SemesterResult")
    @PostMapping
    public SemesterResult create(@RequestBody SemesterResult result) {
        return repository.save(result);
    }

    @Operation(summary = "Delete a SemesterResult")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
