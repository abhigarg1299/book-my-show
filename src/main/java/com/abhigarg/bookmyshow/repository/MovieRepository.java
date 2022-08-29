package com.abhigarg.bookmyshow.repository;

import com.abhigarg.bookmyshow.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findMovieByName(String name);

    @Query("SELECT m FROM Movie m WHERE m.isActive=true")
    List<Movie> findActiveMovies();
}

