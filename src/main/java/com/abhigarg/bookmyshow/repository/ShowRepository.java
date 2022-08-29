package com.abhigarg.bookmyshow.repository;

import com.abhigarg.bookmyshow.entities.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    List<Show> findShowByMovieIdAndHallIdAndShowDate(int movieId, int hallId, java.util.Date showDate);

    Show findShowById(int id);

    @Query("UPDATE Show s set s.availableSeats=?2 where s.id=?1")
    @Modifying
    void updateAvailableSeats(int showId, int availableSeats);
}