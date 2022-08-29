package com.abhigarg.bookmyshow.service.impl;

import com.abhigarg.bookmyshow.entities.Movie;
import com.abhigarg.bookmyshow.exceptions.BadRequestException;
import com.abhigarg.bookmyshow.pojo.ApiResponse;
import com.abhigarg.bookmyshow.repository.MovieRepository;
import com.abhigarg.bookmyshow.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository moviesRepository;

    @Override
    public Movie Add(Movie movie) {
        movie.setCreatedAt(new Date());
        movie.setUpdatedAt(new Date());
        return moviesRepository.save(movie);
    }

    @Override
    public List<Movie> getMovieByName(String name) {
        List<Movie> MovielList = moviesRepository.findMovieByName(name);
        if (null == name) {
            throw new BadRequestException(new ApiResponse(false, "movie name is empty ", HttpStatus.BAD_REQUEST));
        }

        if (MovielList.size() == 0) {
            throw new BadRequestException(new ApiResponse(false, "no movie found by name " + name, HttpStatus.BAD_REQUEST));
        }
        return MovielList;
    }

    @Override
    public List<Movie> getActiveMovies() {
        List<Movie> movieList = moviesRepository.findActiveMovies();
        if (movieList.size() == 0) {
            throw new BadRequestException(new ApiResponse(false, "no active movies found", HttpStatus.BAD_REQUEST));
        }

        return movieList;
    }
}
