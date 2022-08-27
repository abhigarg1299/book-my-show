package com.abhigarg.bookmyshow.service;


import com.abhigarg.bookmyshow.entities.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User Add(User user);

    User Login(String emailId, String password);
}
