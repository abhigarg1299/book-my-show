package com.abhigarg.bookmyshow.service;

import com.abhigarg.bookmyshow.entities.Theatre;
import com.abhigarg.bookmyshow.pojo.GetTheatreRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TheatreService {
    Theatre Add(Theatre theatre);

    List<Theatre> getTheatreByNameAndRegionName(GetTheatreRequest getTheatreRequest);

    List<Theatre> getTheatreByName(String theatreName);
}