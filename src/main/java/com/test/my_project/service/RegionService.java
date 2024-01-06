package com.test.my_project.service;

import com.test.my_project.entity.Region;
import com.test.my_project.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void deleteRegion(Long regionId) {
        regionRepository.deleteById(regionId);
    }
}
