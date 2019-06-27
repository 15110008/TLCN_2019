package com.travel.service.impl;

import com.travel.api.v1.dto.TourDto;
import com.travel.api.v1.mapper.TourMapper;
import com.travel.model.Tour;
import com.travel.repository.TourRepository;
import com.travel.service.TourService;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourServiceImpl.class);

    private final TourRepository tourRepository;

    private final FileStorageService fileStorageService;

    private final TourMapper tourMapper = TourMapper.INSTANCE;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, FileStorageService fileStorageService) {
        this.tourRepository = tourRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Optional<TourDto> getTourById(long id) {
        // find and check exist
        Tour tour = tourRepository.findByIdAndEnable(id, true)
                .orElseThrow(() -> new ObjectNotFoundException(id, Tour.class.getSimpleName()));

        return Optional.ofNullable(tourMapper.entityToDto(tour));
    }

    @Override
    public Page<TourDto> getAllTours(Pageable pageable) {
        return tourRepository.findAllByEnable(true, pageable).map(tourMapper::entityToDto);
    }

    @Override
    public Optional<TourDto> addTour(TourDto tourDto) throws Exception {
        // map to entity
        Tour tour = tourMapper.dtoToEntity(tourDto);
        tour.setEnable(true);
        // save entity
        tour = tourRepository.save(tour);

        // store empty file
        String fileName = fileStorageService.storeFile(String.format("%s-%d.html", tour.getUrl(), tour.getId()),
                "Nội dung đang cập nhật.");
        tour.setFileContentUrl(fileName);
        tour = tourRepository.save(tour);

        return Optional.of(tourMapper.entityToDto(tour));
    }

    @Override
    public Optional<TourDto> updateTour(long id, TourDto tourDto) {
        // for check exist
        Tour tourEnity = tourRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Tour.class.getSimpleName()));

        // copy new data of dto to entity
        Tour tour = tourMapper.dtoToEntity(tourDto);
        tour.setId(id);
        tour.setFileContentUrl(tourEnity.getFileContentUrl());
        tour.setEnable(true);
        // save change
        tour = tourRepository.save(tour);

        return Optional.of(tourMapper.entityToDto(tour));
    }

    @Override
    public Optional<TourDto> deleteTour(long id) {
        Tour tour = tourRepository.findByIdAndEnable(id, true)
                .orElseThrow(() -> new ObjectNotFoundException(id, Tour.class.getSimpleName()));

        // set enable to false as delete
        tour.setEnable(false);

        // save change
        tour = tourRepository.save(tour);

        return Optional.of(tourMapper.entityToDto(tour));
    }
}
