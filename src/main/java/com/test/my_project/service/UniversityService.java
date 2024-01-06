package com.test.my_project.service;

import com.test.my_project.entity.Region;
import com.test.my_project.entity.University;
import com.test.my_project.repository.RegionRepository;
import com.test.my_project.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    private final UniversityRepository universityRepository;
    private final RegionRepository regionRepository;

    @Autowired
    public UniversityService(UniversityRepository universityRepository, RegionRepository regionRepository) {
        this.universityRepository = universityRepository;
        this.regionRepository = regionRepository;
    }

    public University createUniversity(String universityName, Long regionId) {
        Optional<Region> region = regionRepository.findById(regionId);
        if (region.isEmpty()) {
            return null;
        }

        University university = new University();
        university.setName(universityName);
        university.setRegion(region.get());
        return universityRepository.save(university);
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public University updateUniversity(Long id, University updatedUniversity) throws ChangeSetPersister.NotFoundException {
        Optional<University> existingUniversityOptional = universityRepository.findById(id);

        if (existingUniversityOptional.isPresent()) {
            University existingUniversity = existingUniversityOptional.get();
            existingUniversity.setName(updatedUniversity.getName());

            return universityRepository.save(existingUniversity);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public void deleteUniversityById(Long id) {
        universityRepository.deleteById(id);
    }



}