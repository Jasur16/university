package com.test.my_project.controller;

import com.test.my_project.entity.University;
import com.test.my_project.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<List<University>> getAllUniversities() {
        List<University> universities = universityService.getAllUniversities();
        return new ResponseEntity<>(universities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<University> createUniversity(@RequestBody UniversityRequest request) {
        University university = universityService.createUniversity(request.getName(), request.getRegionId());
        if (university == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(university, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @RequestBody University updatedUniversity) throws ChangeSetPersister.NotFoundException {
        University university = universityService.updateUniversity(id, updatedUniversity);
        return new ResponseEntity<>(university, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUniversityById(@PathVariable Long id) {
        universityService.deleteUniversityById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
