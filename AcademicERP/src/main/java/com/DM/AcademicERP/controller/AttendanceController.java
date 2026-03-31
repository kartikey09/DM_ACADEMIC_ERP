package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.Attendance;
import com.DM.AcademicERP.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Attendance API", description = "Endpoints for managing Attendance")
@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    @Autowired
    private AttendanceRepository repository;

    @Operation(summary = "Get all Attendances")
    @GetMapping
    public List<Attendance> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Create a new Attendance")
    @PostMapping
    public Attendance create(@RequestBody Attendance attendance) {
        return repository.save(attendance);
    }

    @Operation(summary = "Delete a Attendance")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
