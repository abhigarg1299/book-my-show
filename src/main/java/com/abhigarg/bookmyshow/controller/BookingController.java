package com.abhigarg.bookmyshow.controller;

import com.abhigarg.bookmyshow.entities.Booking;
import com.abhigarg.bookmyshow.entities.Show;
import com.abhigarg.bookmyshow.pojo.GetShowRequest;
import com.abhigarg.bookmyshow.service.BookingService;
import com.abhigarg.bookmyshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<Booking> Add(@Valid @RequestBody Booking booking) {
        Booking newBooking = bookingService.add(booking);

        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }


    @GetMapping("/{user-id}")
    public ResponseEntity<List<Booking>> getBookingByUserId(@PathVariable(name = "user-id") int userId) {
        List<Booking> bookingList = bookingService.getBookingByUserId(userId);

        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<Show>> getTheatreByName(@PathVariable(name = "id") int id) {
//        List<Show> showList = showService.getShowById(id);
//        return new ResponseEntity<>(showList, HttpStatus.OK);
//    }
}