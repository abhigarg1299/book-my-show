package com.abhigarg.bookmyshow.controller;

import com.abhigarg.bookmyshow.entities.Movie;
import com.abhigarg.bookmyshow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> Add(@Valid @RequestBody Movie movie) {
        Movie movieAdded = movieService.Add(movie);

        return new ResponseEntity<>(movieAdded, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Movie>> getMovieByName(@PathVariable(name = "name") String name) {
        List<Movie> MovieList = movieService.getMovieByName(name);

        return new ResponseEntity<>(MovieList, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Movie>> getActiveMovies() {
        List<Movie> MovieList = movieService.getActiveMovies();

        return new ResponseEntity<>(MovieList, HttpStatus.OK);
    }
}
