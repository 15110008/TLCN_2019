package com.travel.service;

import com.travel.api.v1.dto.PlaceDto;
import com.travel.api.v1.dto.PlanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MapService {

    List<PlaceDto> getPlacesContainsPlan();

    Page<PlanDto> getPlansWithUrlOfPlaces(String url, Pageable pageable);

}
