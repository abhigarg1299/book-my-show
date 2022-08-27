package com.abhigarg.bookmyshow.service;


import com.abhigarg.bookmyshow.entities.User;
import com.abhigarg.bookmyshow.pojo.LoginRequest;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User Add(User user);

    User Login(LoginRequest loginRequest);
}
