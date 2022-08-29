package com.abhigarg.bookmyshow.service;

import com.abhigarg.bookmyshow.entities.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovieService {
    Movie Add(Movie movie);

    List<Movie> getMovieByName(String name);

    List<Movie> getActiveMovies();
}