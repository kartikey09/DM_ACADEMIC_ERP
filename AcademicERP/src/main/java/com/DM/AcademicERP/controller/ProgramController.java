package com.DM.AcademicERP.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import com.DM.AcademicERP.entity.Program;
import com.DM.AcademicERP.repository.ProgramRepository;
import com.DM.AcademicERP.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Program API", description = "Endpoints for managing Program")
@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Operation(summary = "Get all Programs")
    @GetMapping
    public List<Program> getAll() {
        return programRepository.findAll();
    }

    @Operation(summary = "Get Program ('/department/{deptId}')")
    @GetMapping("/department/{deptId}")
    public List<Program> getByDepartment(@PathVariable Long deptId) {
        return programRepository.findByDepartment_DepartmentId(deptId);
    }

    @Operation(summary = "Get Program ('/{id}')")
    @GetMapping("/{id}")
    public ResponseEntity<Program> getById(@PathVariable Long id) {
        return programRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new Program")
    @PostMapping
    public ResponseEntity<Program> create(@RequestBody Program program) {
        if(program.getDepartment() != null && program.getDepartment().getDepartmentId() != null) {
            return departmentRepository.findById(program.getDepartment().getDepartmentId()).map(dept -> {
                program.setDepartment(dept);
                return ResponseEntity.ok(programRepository.save(program));
            }).orElse(ResponseEntity.badRequest().build());
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Update an existing Program")
    @PutMapping("/{id}")
    public ResponseEntity<Program> update(@PathVariable Long id, @RequestBody Program updated) {
        return programRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setType(updated.getType());
            existing.setDurationYears(updated.getDurationYears());
            if(updated.getDepartment() != null && updated.getDepartment().getDepartmentId() != null){
                departmentRepository.findById(updated.getDepartment().getDepartmentId()).ifPresent(existing::setDepartment);
            }
            return ResponseEntity.ok(programRepository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a Program")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
