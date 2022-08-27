package com.abhigarg.bookmyshow.service.impl;

import com.abhigarg.bookmyshow.entities.User;
import com.abhigarg.bookmyshow.exceptions.BadRequestException;
import com.abhigarg.bookmyshow.exceptions.UnauthorizedException;
import com.abhigarg.bookmyshow.pojo.LoginRequest;
import com.abhigarg.bookmyshow.repository.UsersRepository;
import com.abhigarg.bookmyshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User Add(User user) {
        List<User> users = usersRepository.findByEmailId(user.getEmailId());
        if (users.size() > 0) {
            throw new BadRequestException("user already exists");
        }
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        return usersRepository.save(user);
    }

    @Override
    public User Login(LoginRequest loginRequest) {
        List<User> users = usersRepository.findByEmailIdAndPassword(loginRequest.getEmailId(), loginRequest.getPassword());
        if (users.size() == 0) {
            throw new UnauthorizedException("User is not authorized");
        }
        return users.get(0);
    }
}
