package com.DM.AcademicERP.controller;

import com.DM.AcademicERP.entity.CumulativeResult;
import com.DM.AcademicERP.repository.CumulativeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cumulative-results")
public class CumulativeResultController {

    @Autowired
    private CumulativeResultRepository repository;

    @GetMapping
    public List<CumulativeResult> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public CumulativeResult create(@RequestBody CumulativeResult result) {
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
