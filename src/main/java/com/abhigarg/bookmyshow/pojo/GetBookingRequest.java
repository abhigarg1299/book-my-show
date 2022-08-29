package com.abhigarg.bookmyshow.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class GetBookingRequest {
    private static final long serialVersionUID = 7702134516418120340L;

    int userId;

    String userEmail;
}
