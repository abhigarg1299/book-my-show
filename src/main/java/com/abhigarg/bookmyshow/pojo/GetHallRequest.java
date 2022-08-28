package com.abhigarg.bookmyshow.pojo;

import lombok.Data;

@Data
public class GetHallRequest {
    private static final long serialVersionUID = 7702134516418120340L;

    int theatreId;

    String theatreName;

    String name;
}
