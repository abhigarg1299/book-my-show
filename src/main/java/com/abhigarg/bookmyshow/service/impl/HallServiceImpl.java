package com.abhigarg.bookmyshow.service.impl;

import com.abhigarg.bookmyshow.entities.Hall;
import com.abhigarg.bookmyshow.entities.Theatre;
import com.abhigarg.bookmyshow.exceptions.BadRequestException;
import com.abhigarg.bookmyshow.pojo.GetHallRequest;
import com.abhigarg.bookmyshow.repository.HallRepository;
import com.abhigarg.bookmyshow.repository.TheatreRepository;
import com.abhigarg.bookmyshow.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    @Autowired
    private TheatreRepository theatresRepository;
    @Autowired
    private HallRepository hallsRepository;

    @Override
    public Hall Add(Hall hall) {
        hall.setCreatedAt(new Date());
        hall.setUpdatedAt(new Date());
        return hallsRepository.save(hall);
    }


    @Override
    public List<Hall> getHallByNameAndTheatreId(GetHallRequest getHallRequest) {
        List<Theatre> theatres = theatresRepository.findTheatreByName(getHallRequest.getTheatreName());
        if (theatres.size() == 0) {
            throw new BadRequestException("Theatre name is invalid");
        }

        List<Hall> Halls = hallsRepository.findHallByNameAndTheatreId(getHallRequest.getName(), theatres.get(0).getId());
        if (Halls.size() == 0) {
            throw new BadRequestException("HAll name is invalid");
        }
        return Halls;
    }

    @Override
    public List<Hall> getHallByName(String name) {
        List<Hall> HallList = hallsRepository.findHallByName(name);
        if (null == name) {
            throw new BadRequestException("Hall Name is Empty");
        }

        if (HallList.size() == 0) {
            throw new BadRequestException("No Hall in the List");
        }
        return HallList;
    }
}
