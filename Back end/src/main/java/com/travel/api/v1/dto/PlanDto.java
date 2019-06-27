package com.travel.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PlanDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private String url;

    private Date startTime;

    private int numberOfSlot;

    private int numberOfReservedSlot;

    private double adultPrice;

    private double childPrice;

    private Long tourId;

    private List<Long> placeIds;
}
