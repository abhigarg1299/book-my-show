package com.abhigarg.bookmyshow.repository;

import com.abhigarg.bookmyshow.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HallRepository extends JpaRepository<Hall, Integer> {
    List<Hall> findHallByName(String name);

    List<Hall> findHallByNameAndTheatreId(String name, int id);
}

