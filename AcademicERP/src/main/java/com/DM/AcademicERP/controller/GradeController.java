package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.Grade;
import com.DM.AcademicERP.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Grade API", description = "Endpoints for managing Grade")
@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeRepository repository;

    @Operation(summary = "Get all Grades")
    @GetMapping
    public List<Grade> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new Grade")
    @PostMapping
    public Grade create(@RequestBody Grade grade) {
        return repository.save(grade);
    }

    @Operation(summary = "Delete a Grade")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
