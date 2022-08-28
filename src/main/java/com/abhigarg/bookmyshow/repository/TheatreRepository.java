package com.abhigarg.bookmyshow.repository;

import com.abhigarg.bookmyshow.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {
    List<Theatre> findTheatreByNameAndRegionId(String name, int regionId);

    List<Theatre> findTheatreByName(String name);

    Theatre findTheatreByRegionId(int id);
}
