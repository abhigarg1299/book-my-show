package com.abhigarg.bookmyshow.repository;

import com.abhigarg.bookmyshow.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findBookingByUserId(int userId);
}