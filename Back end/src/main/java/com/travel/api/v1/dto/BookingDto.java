package com.travel.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BookingDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String fullName;

    private boolean gender;

    private String email;

    private String phoneNumber;

    private String bankAccount;

    private Date bookingTime;

    private int totalOfTicket;

    private double grandTotal;

    private Long planId;

    @JsonIgnore
    private String userName;

    private List<BookingDetailDto> details;
}
