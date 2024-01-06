package com.test.my_project.controller;

import com.test.my_project.entity.Region;
import com.test.my_project.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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


    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable Long id) {
        Optional<Region> region = regionService.getRegionById(id);

        return region.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        Region createdRegion = regionService.createRegion(region);
        return new ResponseEntity<>(createdRegion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable Long id, @RequestBody Region updatedRegion) throws ChangeSetPersister.NotFoundException {
        Region region = regionService.updateRegion(id, updatedRegion);
        return new ResponseEntity<>(region, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegionById(@PathVariable Long id) {
        regionService.deleteRegionById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}