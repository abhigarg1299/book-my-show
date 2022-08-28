package com.abhigarg.bookmyshow.pojo;

import lombok.Data;

@Data
public class GetTheatreRequest {
    private static final long serialVersionUID = 7702134516418120340L;

    String theatreName;

    String regionName;
}
