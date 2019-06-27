package com.travel.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.travel.contant.TicketType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BookingDetailDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String fullName;

    private boolean gender;

    private String identification;

    private Date birthDate;

    private TicketType ticketType;

    private Long bookingId;
}
