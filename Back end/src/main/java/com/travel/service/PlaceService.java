package com.travel.service;

import com.travel.api.v1.dto.PlaceDto;
import com.travel.model.Place;

import java.util.List;
import java.util.Optional;

public interface PlaceService {

   Optional<PlaceDto> getPlaceById(Long id);

   List<PlaceDto> getPlaces(String search);

   List<PlaceDto> getPlacesByPlanId(Long planId);

   Optional<PlaceDto> addPlace(PlaceDto newPlace);

   Optional<PlaceDto> editPlaceById(Long id, PlaceDto planDto);

   Optional<PlaceDto> deletePlaceById(Long id);

   List<Place> searchPlace(String searchString);
}
