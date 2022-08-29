package com.abhigarg.bookmyshow.service;


import com.abhigarg.bookmyshow.entities.Booking;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public interface BookingService {
    Booking add(Booking booking);
    List<Booking> getBookingByUserId(int userId);
}