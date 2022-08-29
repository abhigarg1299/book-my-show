package com.abhigarg.bookmyshow.service.impl;

import com.abhigarg.bookmyshow.entities.Hall;
import com.abhigarg.bookmyshow.entities.Movie;
import com.abhigarg.bookmyshow.entities.Show;
import com.abhigarg.bookmyshow.exceptions.BadRequestException;
import com.abhigarg.bookmyshow.pojo.ApiResponse;
import com.abhigarg.bookmyshow.pojo.GetShowRequest;
import com.abhigarg.bookmyshow.repository.HallRepository;
import com.abhigarg.bookmyshow.repository.MovieRepository;
import com.abhigarg.bookmyshow.repository.ShowRepository;
import com.abhigarg.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showsRepository;

    @Autowired
    private MovieRepository moviesRepository;

    @Autowired
    private HallRepository hallsRepository;


    @Override
    public Show Add(Show show) {
        show.setCreatedAt(new Date());

        show.setUpdatedAt(new Date());
        return showsRepository.save(show);
    }

    @Override
    public List<Show> getShowByMovieIdAndHallIdAndShowDate(GetShowRequest getShowRequest) {
        List<Movie> movieList = moviesRepository.findMovieByName(getShowRequest.getMovieName());
        if (movieList.isEmpty()) {
            throw new BadRequestException(new ApiResponse(false, "no movie found by name ", HttpStatus.BAD_REQUEST));
        }
        List<Hall> hallList = hallsRepository.findHallByName(getShowRequest.getHallName());
        if (hallList.isEmpty()) {
            throw new BadRequestException(new ApiResponse(false, "no hall found  ", HttpStatus.BAD_REQUEST));
        }

        List<Show> Shows = showsRepository.findShowByMovieIdAndHallIdAndShowDate(movieList.get(0).getId(), hallList.get(0).getId(), getShowRequest.getShowDate());

        if (Shows.isEmpty()) {
            throw new BadRequestException(new ApiResponse(false, "no shows found  ", HttpStatus.BAD_REQUEST));
        }
        return Shows;
    }

    @Override

    public Show getShowById(int id) {
        Show show = showsRepository.findShowById(id);
        if (null == show) {
            throw new BadRequestException(new ApiResponse(false, "no show found ", HttpStatus.BAD_REQUEST));
        }

        return show;
    }
}

