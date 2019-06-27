package com.travel.service.impl;

import com.travel.api.v1.dto.PlanDto;
import com.travel.api.v1.mapper.PlanMapper;
import com.travel.model.Place;
import com.travel.model.Plan;
import com.travel.model.Tour;
import com.travel.repository.PlaceRepository;
import com.travel.repository.PlanRepository;
import com.travel.repository.TourRepository;
import com.travel.service.PlanService;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlanServiceImpl implements PlanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanServiceImpl.class);

    private final PlanMapper planMapper = PlanMapper.INSTANCE;

    private final PlanRepository planRepository;

    private final PlaceRepository placeRepository;

    private final TourRepository tourRepository;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository, PlaceRepository placeRepository, TourRepository tourRepository) {
        this.planRepository = planRepository;
        this.placeRepository = placeRepository;
        this.tourRepository = tourRepository;
    }

    @Override
    public Optional<PlanDto> getPlanById(Long id) {
        // find and check exist
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Plan.class.getSimpleName()));

        return Optional.of(planMapper.entityToDtoWithTourId(plan));
    }

    @Override
    public Page<PlanDto> getPlans(Pageable pageable) {
        return planRepository.findAll(pageable)
                .map(planMapper::entityToDtoWithTourId);
    }

    @Override
    public Page<PlanDto> getPlansByPlaceId(Long placeId, Pageable pageable) {
        // find and check exist
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new ObjectNotFoundException(placeId, Place.class.getSimpleName()));

        return planRepository.findAllByPlacesIs(place, pageable)
                .map(planMapper::entityToDtoWithTourId);
    }

    @Override
    public Optional<PlanDto> addPlan(PlanDto newPlan) {
        // get as check exist tour
        Tour tour = tourRepository.findById(newPlan.getTourId())
                .orElseThrow(() -> new ObjectNotFoundException(newPlan.getTourId(), Tour.class.getSimpleName()));
        // get as check places
        Set<Place> places = placeRepository.findAllByIdIsIn(newPlan.getPlaceIds());

        // map to entity
        Plan plan = planMapper.dtoToEntity(newPlan);
        plan.setTour(tour);
        plan.setPlaces(places);

        // save entity
        plan = planRepository.save(plan);
        return Optional.of(planMapper.entityToDtoWithTourId(plan));
    }

    @Override
    public Optional<PlanDto> editPlanById(Long id, PlanDto planDto) {
        // get as check exist tour
        Tour tour = tourRepository.findById(planDto.getTourId())
                .orElseThrow(() -> new ObjectNotFoundException(planDto.getTourId(), Tour.class.getSimpleName()));

        // for check exist
        planRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Plan.class.getSimpleName()));

        // get as check places
        Set<Place> places = placeRepository.findAllByIdIsIn(planDto.getPlaceIds());

        // copy new data of dto to entity
        Plan plan = planMapper.dtoToEntity(planDto);
        plan.setId(id);
        plan.setTour(tour);
        plan.setPlaces(places);

        // save change
        plan = planRepository.save(plan);

        PlanDto updatedPlanDto = planMapper.entityToDto(plan);
        updatedPlanDto.setPlaceIds(planDto.getPlaceIds());
        return Optional.of(updatedPlanDto);
    }

    @Override
    public Optional<PlanDto> deletePlanById(Long id) {
        // find and check exist
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Plan.class.getSimpleName()));

        // save change
        planRepository.deleteById(id);

        return Optional.of(planMapper.entityToDtoWithTourId(plan));
    }
}
