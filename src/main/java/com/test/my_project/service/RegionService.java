package com.test.my_project.service;

import com.test.my_project.entity.Region;
import com.test.my_project.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(Long regionId) {
        return regionRepository.findById(regionId);
    }

    public Region createRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region updateRegion(Long id, Region updatedRegion) throws ChangeSetPersister.NotFoundException {
        Optional<Region> existingRegionOptional = regionRepository.findById(id);

        if (existingRegionOptional.isPresent()) {
            Region existingRegion = existingRegionOptional.get();
            existingRegion.setName(updatedRegion.getName());

            return regionRepository.save(existingRegion);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    public void deleteRegionById(Long id) {
        regionRepository.deleteById(id);
    }
}
