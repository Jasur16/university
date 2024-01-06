package com.test.my_project.controller;

import com.test.my_project.entity.Region;
import com.test.my_project.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/regions")
public class RegionController {
    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions() {
        List<Region> regions = regionService.getAllRegions();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }


    @GetMapping("/{regionId}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long regionId) {
        Optional<Region> region = regionService.getRegionById(regionId);
        return region.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        Region createdRegion = regionService.createRegion(region);
        return new ResponseEntity<>(createdRegion, HttpStatus.CREATED);
    }

}