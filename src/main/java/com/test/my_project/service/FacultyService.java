package com.test.my_project.service;


import com.test.my_project.entity.Faculty;
import com.test.my_project.entity.University;
import com.test.my_project.repository.FacultyRepository;
import com.test.my_project.repository.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.universityRepository = universityRepository;
    }

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Faculty with id " + facultyId + " not found"
                ));
    }

    public List<Faculty> getFacultiesByUniversityId(Long universityId) {
        return facultyRepository.findByUniversityId(universityId);
    }

    public Faculty createFaculty(Faculty faculty, Long universityId) {
        University university = universityRepository.findById(universityId)
                .orElseThrow(() -> new EntityNotFoundException("University with id " + universityId + " not found"));

        faculty.setUniversity(university);
        return facultyRepository.save(faculty);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        Faculty oldFaculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new EntityNotFoundException("Faculty with id " + facultyId + " not found"));
        oldFaculty.setName(faculty.getName());
        return facultyRepository.save(oldFaculty);
    }

    public void deleteFaculty(Long facultyId) {
        if(!facultyRepository.existsById(facultyId)) {
            throw new EntityNotFoundException("Faculty with id " + facultyId + " not found");
        }
        facultyRepository.deleteById(facultyId);
    }
}
