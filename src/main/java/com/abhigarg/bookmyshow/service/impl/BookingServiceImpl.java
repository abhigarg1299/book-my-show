package com.abhigarg.bookmyshow.service.impl;

import com.abhigarg.bookmyshow.entities.Booking;
import com.abhigarg.bookmyshow.entities.Show;
import com.abhigarg.bookmyshow.entities.User;
import com.abhigarg.bookmyshow.exceptions.BadRequestException;
import com.abhigarg.bookmyshow.pojo.ApiResponse;
import com.abhigarg.bookmyshow.repository.BookingRepository;
import com.abhigarg.bookmyshow.repository.ShowRepository;
import com.abhigarg.bookmyshow.repository.UsersRepository;
import com.abhigarg.bookmyshow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BookingRepository bookingsRepository;

    @Autowired
    private ShowRepository showRepository;


    @Override
    @Transactional
    public Booking add(Booking booking) {
        Show show = showRepository.findShowById(booking.getShowId());
        if (null == show) {
            throw new BadRequestException(new ApiResponse(false, "no show found ", HttpStatus.BAD_REQUEST));
        }

        int numberOfSeatsLeft = show.getAvailableSeats() - booking.getSeats();
        if (numberOfSeatsLeft <= 0) {
            throw new BadRequestException(new ApiResponse(false, "show is full", HttpStatus.BAD_REQUEST));
        }

        showRepository.updateAvailableSeats(show.getId(), numberOfSeatsLeft);

        booking.setCreatedAt(new Date());
        booking.setUpdatedAt(new Date());
        return bookingsRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingByUserId(int userId) {
        User user = usersRepository.findUserById(userId);
        if (null == user) {
            throw new BadRequestException(new ApiResponse(false, "no user found ", HttpStatus.BAD_REQUEST));
        }

        List<Booking> bookingList = bookingsRepository.findBookingByUserId(user.getId());
        if (bookingList.isEmpty()) {
            throw new BadRequestException(new ApiResponse(false, "no bookings found for user " + user.getFirstName(), HttpStatus.BAD_REQUEST));
        }

        return bookingList;
    }
}