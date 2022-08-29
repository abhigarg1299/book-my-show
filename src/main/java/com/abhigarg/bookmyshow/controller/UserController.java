package com.abhigarg.bookmyshow.controller;

import com.abhigarg.bookmyshow.entities.Show;
import com.abhigarg.bookmyshow.entities.User;
import com.abhigarg.bookmyshow.pojo.LoginRequest;
import com.abhigarg.bookmyshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = userService.Add(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<User> Login(@Valid @RequestBody LoginRequest loginRequest) {
        User newUser = userService.Login(loginRequest);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
}
