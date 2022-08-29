package com.abhigarg.bookmyshow.service;

import com.abhigarg.bookmyshow.entities.Show;
import com.abhigarg.bookmyshow.pojo.GetShowRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShowService {
    Show Add(Show show);
    List<Show> getShowByMovieIdAndHallIdAndShowDate(GetShowRequest getShowRequest);
    List<Show> getShowById(int id);
}