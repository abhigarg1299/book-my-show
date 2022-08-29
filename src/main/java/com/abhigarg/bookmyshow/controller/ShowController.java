package com.abhigarg.bookmyshow.controller;

import com.abhigarg.bookmyshow.entities.Show;
import com.abhigarg.bookmyshow.pojo.GetShowRequest;
import com.abhigarg.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping
    public ResponseEntity<Show> Add(@Valid @RequestBody Show show) {
        Show newShow = showService.Add(show);

        return new ResponseEntity<>(newShow, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Show>> getShowByMovieIdAndHallIdAndShowDate(@RequestBody GetShowRequest getShowRequest) {
        List<Show> showList = showService.getShowByMovieIdAndHallIdAndShowDate(getShowRequest);

        return new ResponseEntity<>(showList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Show>> getTheatreByName(@PathVariable(name = "id") int id) {
        List<Show> showList = showService.getShowById(id);
        return new ResponseEntity<>(showList, HttpStatus.OK);
    }
}