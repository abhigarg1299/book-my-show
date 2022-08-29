package com.abhigarg.bookmyshow.entities;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "BOOKINGS")
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "SHOW_ID")
    private int showId;

    @Column(name = "SEATS")
    private int seats;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date updatedAt;
}
