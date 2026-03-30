package com.DM.AcademicERP.controller;

import com.DM.AcademicERP.entity.Grade;
import com.DM.AcademicERP.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeRepository repository;

    @GetMapping
    public List<Grade> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Grade create(@RequestBody Grade grade) {
        return repository.save(grade);
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
