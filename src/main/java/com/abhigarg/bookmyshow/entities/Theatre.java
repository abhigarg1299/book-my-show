package com.abhigarg.bookmyshow.entities;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "THEATRES")
@Data
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "ADDRESS")
    @NotBlank(message = "Address is mandatory")
    private String address;

    @Column(name = "REGION_ID")
    @NotBlank(message = "Region Id is mandatory")
    private int regionId;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date updatedAt;
}
