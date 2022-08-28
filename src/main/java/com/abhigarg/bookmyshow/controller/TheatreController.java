package com.abhigarg.bookmyshow.controller;

import com.abhigarg.bookmyshow.entities.Theatre;
import com.abhigarg.bookmyshow.pojo.GetTheatreRequest;
import com.abhigarg.bookmyshow.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    private TheatreService theatreService;


    @PostMapping
    public ResponseEntity<Theatre> Add(@RequestBody Theatre theatre) {
        Theatre newTheatre = theatreService.Add(theatre);

        return new ResponseEntity<>(newTheatre, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Theatre>> getTheatreByNameAndRegionName(@RequestBody GetTheatreRequest getTheatreRequest) {
        List<Theatre> theatreList = theatreService.getTheatreByNameAndRegionName(getTheatreRequest);

        return new ResponseEntity<>(theatreList, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Theatre>> getTheatreByName(@PathVariable(name = "name") String theatreName) {
        List<Theatre> theatreList = theatreService.getTheatreByName(theatreName);

        return new ResponseEntity<>(theatreList, HttpStatus.OK);
    }
}