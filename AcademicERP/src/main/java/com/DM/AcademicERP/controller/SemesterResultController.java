package com.DM.AcademicERP.controller;

import com.DM.AcademicERP.entity.SemesterResult;
import com.DM.AcademicERP.repository.SemesterResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semester-results")
public class SemesterResultController {

    @Autowired
    private SemesterResultRepository repository;

    @GetMapping
    public List<SemesterResult> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public SemesterResult create(@RequestBody SemesterResult result) {
        return repository.save(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
