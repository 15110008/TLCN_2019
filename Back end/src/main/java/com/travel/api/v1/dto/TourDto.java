package com.travel.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TourDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private String url;

    private String imageUrl;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String fileContentUrl;

    private String description;

    private int numberOfDate;

    private int numberOfNight;

}
