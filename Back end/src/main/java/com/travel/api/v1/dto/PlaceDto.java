package com.travel.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PlaceDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    private String url;

    private String imageUrl;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long parentId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private PlaceDto parent;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long countPlan = 0l;
}
