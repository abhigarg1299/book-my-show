package com.abhigarg.bookmyshow.service;


import com.abhigarg.bookmyshow.entities.Hall;
import com.abhigarg.bookmyshow.pojo.GetHallRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HallService {
    Hall Add(Hall hall);

    List<Hall> getHallByNameAndTheatreId(GetHallRequest getHallRequest);

    List<Hall> getHallByName(String name);
}