package com.abhigarg.bookmyshow.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "SHOWS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_MOVIES"))
    @Column(name = "MOVIE_ID")
    private int movieId;
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_HALLS"))
    @Column(name = "HALL_ID")
    private int hallId;

    @Column(name = "SHOW_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "show data is mandatory")
    private Date showDate;

    @JoinColumn(foreignKey = @ForeignKey(name = "FK_TIMINGS"))
    @Column(name = "TIMING_ID")
    private int timingId;

    @Column(name = "SEAT_PRICE")
    @Min(200)
    @Max(250)
    private int seatPrice;

    @Column(name = "AVAILABLE_SEATS")
    private int availableSeats;

    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date createdAt;

    @Column(name = "UPDATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    @Nullable
    private Date updatedAt;
}
