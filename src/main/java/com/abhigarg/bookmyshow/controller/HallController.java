package com.abhigarg.bookmyshow.controller;

import com.abhigarg.bookmyshow.entities.Hall;
import com.abhigarg.bookmyshow.pojo.GetHallRequest;
import com.abhigarg.bookmyshow.pojo.GetTheatreRequest;
import com.abhigarg.bookmyshow.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @PostMapping
    public ResponseEntity<Hall> Add(@Valid @RequestBody Hall hall) {
        Hall hallAdded = hallService.Add(hall);

        return new ResponseEntity<>(hallAdded, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Hall>> getHallByNameAndTheatreId(@RequestBody GetHallRequest getHallRequest) {
        List<Hall> hallList = hallService.getHallByNameAndTheatreId(getHallRequest);

        return new ResponseEntity<>(hallList, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Hall>> getHallByName(@PathVariable(name = "name") String name) {
        List<Hall> hallList = hallService.getHallByName(name);

        return new ResponseEntity<>(hallList, HttpStatus.OK);
    }
}
