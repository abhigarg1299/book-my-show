package com.abhigarg.bookmyshow.service.impl;

import antlr.StringUtils;
import com.abhigarg.bookmyshow.entities.Region;
import com.abhigarg.bookmyshow.entities.Theatre;
import com.abhigarg.bookmyshow.exceptions.BadRequestException;
import com.abhigarg.bookmyshow.pojo.ApiResponse;
import com.abhigarg.bookmyshow.pojo.GetTheatreRequest;
import com.abhigarg.bookmyshow.repository.RegionRepository;
import com.abhigarg.bookmyshow.repository.TheatreRepository;
import com.abhigarg.bookmyshow.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TheatreServiceImpl implements TheatreService {

    @Autowired
    private TheatreRepository theatresRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Theatre Add(Theatre theatre) {
        theatre.setCreatedAt(new Date());

        theatre.setUpdatedAt(new Date());
        return theatresRepository.save(theatre);
    }

    @Override
    public List<Theatre> getTheatreByNameAndRegionName(GetTheatreRequest getTheatreRequest) {
        List<Region> regionList = regionRepository.findRegionByName(getTheatreRequest.getRegionName());
        if (regionList.isEmpty()) {
            throw new BadRequestException("region name is invalid");
        }

        List<Theatre> Theatres = theatresRepository.findTheatreByNameAndRegionId(getTheatreRequest.getTheatreName(), regionList.get(0).getId());

        if (Theatres.isEmpty()) {
            throw new BadRequestException("no theatres found");
        }
        return Theatres;
    }

    @Override
    public List<Theatre> getTheatreByName(String theatreName) {
        List<Theatre> theatreList = theatresRepository.findTheatreByName(theatreName);
        if (null == theatreName) {
            throw new BadRequestException("theatre name is Empty");
        }

        if (theatreList.size() == 0) {
            throw new BadRequestException(new ApiResponse(false, "no theatres found by name " + theatreName, HttpStatus.BAD_REQUEST));
        }

        return theatreList;
    }
}

