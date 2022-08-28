package com.abhigarg.bookmyshow.controller;

import com.abhigarg.bookmyshow.entities.Region;
import com.abhigarg.bookmyshow.service.RegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;


    @PostMapping
    public ResponseEntity<Region> addRegion(@Valid @RequestBody Region region) {
        Region newRegion = regionService.Add(region);

        return new ResponseEntity<>(newRegion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegion(@PathVariable(name = "id") int id) {
        Region region = regionService.GetRegionById(id);

        return new ResponseEntity<>(region, HttpStatus.OK);
    }
}

