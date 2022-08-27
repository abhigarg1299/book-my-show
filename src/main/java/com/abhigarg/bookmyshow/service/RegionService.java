package com.abhigarg.bookmyshow.service;


import com.abhigarg.bookmyshow.entities.Region;
import org.springframework.stereotype.Component;

@Component
public interface RegionService {
    Region Add(Region region);

    Region GetRegionById(int id);
}