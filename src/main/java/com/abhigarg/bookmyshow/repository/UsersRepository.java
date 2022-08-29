package com.abhigarg.bookmyshow.repository;

import com.abhigarg.bookmyshow.entities.Show;
import com.abhigarg.bookmyshow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    List<User> findByEmailIdAndPassword(String emailId, String password);

    List<User> findByEmailId(String emailId);

    User findUserById(int id);
}