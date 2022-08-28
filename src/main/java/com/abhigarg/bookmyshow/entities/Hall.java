package com.abhigarg.bookmyshow.entities;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "HALLS")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "SEATS")
    @Min(6)
    private int seats;

    @Column(name = "THEATRE_ID")
    private int theatreId;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date updatedAt;
}
