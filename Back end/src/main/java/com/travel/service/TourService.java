package com.travel.service;

import com.travel.api.v1.dto.TourDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TourService {

    Optional<TourDto> getTourById(long id);

    Page<TourDto> getAllTours(Pageable pageable);

    Optional<TourDto> addTour(TourDto tourDto) throws Exception;

    Optional<TourDto> updateTour(long id, TourDto tourDto);

    Optional<TourDto> deleteTour(long id);
}
