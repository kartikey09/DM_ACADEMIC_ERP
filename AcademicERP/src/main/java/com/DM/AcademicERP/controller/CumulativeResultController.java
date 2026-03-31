package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.CumulativeResult;
import com.DM.AcademicERP.repository.CumulativeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CumulativeResult API", description = "Endpoints for managing CumulativeResult")
@RestController
@RequestMapping("/api/cumulative-results")
public class CumulativeResultController {

    @Autowired
    private CumulativeResultRepository repository;

    @Operation(summary = "Get all CumulativeResults")
    @GetMapping
    public List<CumulativeResult> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new CumulativeResult")
    @PostMapping
    public CumulativeResult create(@RequestBody CumulativeResult result) {
        return repository.save(result);
    }

    @Operation(summary = "Delete a CumulativeResult")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
