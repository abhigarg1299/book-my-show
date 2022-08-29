package com.abhigarg.bookmyshow.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class GetShowRequest {
    private static final long serialVersionUID = 7702134516418120340L;

    int hallId;
    int movieId;

    String movieName;

    String hallName;

    Date showDate;
}
