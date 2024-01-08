package com.test.my_project.controller;

import com.test.my_project.entity.Faculty;
import com.test.my_project.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<Faculty>> getAllFaculties() {
        List<Faculty> faculties = facultyService.getAllFaculties();
        return new ResponseEntity<>(faculties, HttpStatus.OK);
    }

    @GetMapping("/{facultyId}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long facultyId) {
        Faculty faculty = facultyService.getFacultyById(facultyId);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }

    @GetMapping("/university/{universityId}")
    public ResponseEntity<List<Faculty>> getFacultiesByUniversityId(@PathVariable Long universityId) {
        List<Faculty> faculties = facultyService.getFacultiesByUniversityId(universityId);
        return new ResponseEntity<>(faculties, HttpStatus.OK);
    }

    @PostMapping("/universities/{universityId}")
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty, @PathVariable Long universityId) {
        Faculty createdFaculty = facultyService.createFaculty(faculty, universityId);
        if (createdFaculty == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(createdFaculty, HttpStatus.CREATED);
    }

    @PutMapping("/{facultyId}")
    public ResponseEntity<Faculty> updateFaculty(@PathVariable Long facultyId, @RequestBody Faculty faculty) {
        Faculty updateFaculty = facultyService.updateFaculty(facultyId, faculty);
        return new ResponseEntity<>(updateFaculty, HttpStatus.OK);
    }

    @DeleteMapping("/{facultyId}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long facultyId) {
        facultyService.deleteFaculty(facultyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
