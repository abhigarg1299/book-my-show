package com.abhigarg.bookmyshow.service.impl;

import com.abhigarg.bookmyshow.entities.Region;
import com.abhigarg.bookmyshow.exceptions.BadRequestException;
import com.abhigarg.bookmyshow.pojo.RegionResponse;
import com.abhigarg.bookmyshow.repository.RegionRepository;
import com.abhigarg.bookmyshow.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionsRepository;

    @Override
    public Region Add(Region region) {
        List<Region> regions = regionsRepository.findRegionByName(region.getName());
        if (regions.size() > 0) {
            throw new BadRequestException("region already exists");
        }
        region.setCreatedAt(new Date());
        region.setUpdatedAt(new Date());

        return regionsRepository.save(region);
    }

    @Override
    public Region GetRegionById(int id) {
        Region region = regionsRepository.findRegionById(id);
        if (null == region) {
            throw new BadRequestException("region id not valid");
        }
        return region;
    }


}
