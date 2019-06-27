package com.travel.model;

import com.travel.contant.ProcessType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private boolean gender;

    private String email;

    private String phoneNumber;

    private String bankAccount;

    private Date bookingTime;

    private int totalOfTicket;

    private double grandTotal;

    @Enumerated(EnumType.STRING)
    private ProcessType processType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Plan plan;

    @OneToMany(mappedBy = "booking",fetch = FetchType.EAGER)
    private Set<BookingDetail> bookingDetails = new HashSet();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;
}
