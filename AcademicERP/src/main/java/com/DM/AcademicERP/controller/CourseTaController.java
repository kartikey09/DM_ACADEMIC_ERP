package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.CourseTa;
import com.DM.AcademicERP.entity.CourseTaId;
import com.DM.AcademicERP.repository.CourseTaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CourseTa API", description = "Endpoints for managing CourseTa")
@RestController
@RequestMapping("/api/offerings/{offeringId}/tas")
public class CourseTaController {

    @Autowired
    private CourseTaRepository repository;

    @Operation(summary = "Get all CourseTas")
    @GetMapping
    public List<CourseTa> getTas(@PathVariable Long offeringId) {
        return repository.findByCourseOffering_OfferingId(offeringId);
    }

    @Operation(summary = "Create a new CourseTa")
    @PostMapping
    public CourseTa assignTa(@PathVariable Long offeringId, @RequestBody CourseTa ta) {
        return repository.save(ta);
    }

    @Operation(summary = "Delete a CourseTa")
    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> removeTa(@PathVariable Long offeringId, @PathVariable Long studentId) {
        CourseTaId id = new CourseTaId(studentId, offeringId);
        if(repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
