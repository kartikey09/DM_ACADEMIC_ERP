package com.DM.AcademicERP.controller;

import com.DM.AcademicERP.entity.CourseTa;
import com.DM.AcademicERP.entity.CourseTaId;
import com.DM.AcademicERP.repository.CourseTaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offerings/{offeringId}/tas")
public class CourseTaController {

    @Autowired
    private CourseTaRepository repository;

    @GetMapping
    public List<CourseTa> getTas(@PathVariable Long offeringId) {
        return repository.findByCourseOffering_OfferingId(offeringId);
    }

    @PostMapping
    public CourseTa assignTa(@PathVariable Long offeringId, @RequestBody CourseTa ta) {
        return repository.save(ta);
    }

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
