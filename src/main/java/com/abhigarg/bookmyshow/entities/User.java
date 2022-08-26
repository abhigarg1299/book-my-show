package com.abhigarg.bookmyshow.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
@Data
public class User extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "PASSWORD")
    private String password;
}
