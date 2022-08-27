package com.abhigarg.bookmyshow.repository;

import com.abhigarg.bookmyshow.entities.Region;
import com.abhigarg.bookmyshow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    Region findRegionById(int Id);

    List<Region> findRegionByName(String name);
}
