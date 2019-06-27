package com.travel.model;

import com.travel.contant.TicketType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking_details")
@Getter
@Setter
public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private boolean gender;

    private String identification;

    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    private Booking booking;

}
