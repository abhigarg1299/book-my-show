package com.abhigarg.bookmyshow.repository;

import com.abhigarg.bookmyshow.entities.Show;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    List<Show> findShowByMovieIdAndHallIdAndShowDate(int movieId, int hallId, java.util.Date showDate);

    List<Show> findShowById(int id);
}

//    InsertShow(*entities.Show) error
//    FetchShowByMovieIdHallIdShowDate(entities.Show) ([]entities.Show, error)
//        FetchShowByShowId(int) ([]entities.Show, error)
//        UpdateSeatsByShowId(int, int) error