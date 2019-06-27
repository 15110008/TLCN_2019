package com.travel.service.impl;

import com.travel.api.v1.dto.PlaceDto;
import com.travel.api.v1.mapper.PlaceMapper;
import com.travel.model.Place;
import com.travel.model.Plan;
import com.travel.repository.PlaceRepository;
import com.travel.repository.PlanRepository;
import com.travel.service.PlaceService;
import com.travel.specification.PlaceSpecification;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceServiceImpl.class);

    private final PlaceMapper placeMapper = PlaceMapper.INSTANCE;

    private final PlaceRepository placeRepository;

    private final PlanRepository planRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository, PlanRepository planRepository) {
        this.placeRepository = placeRepository;
        this.planRepository = planRepository;
    }

    @Override
    public Optional<PlaceDto> getPlaceById(Long id) {
        //  find and check exist
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Place.class.getSimpleName()));
        // convert to dto
        return Optional.of(placeMapper.entityToDto(place));
    }

    @Override
    public List<PlaceDto> getPlaces(String search) {
        return searchPlace(search).stream() // search place by url field
                .map(placeMapper::entityToDto) // convert to dto
                .collect(Collectors.toList());
    }

    @Override
    public List<PlaceDto> getPlacesByPlanId(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new ObjectNotFoundException(planId, Plan.class.getSimpleName())) // check exist
                .getPlaces().stream()
                .map(placeMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlaceDto> addPlace(PlaceDto newPlace) {
        // map to entity
        Place place = placeMapper.dtoToEntity(newPlace);

        // save entity
        place = placeRepository.save(place);
        return Optional.of(placeMapper.entityToDto(place));
    }

    @Override
    public Optional<PlaceDto> editPlaceById(Long id, PlaceDto placeDto) {
        // for check exist
        placeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Place.class.getSimpleName()));

        // copy new data of dto to entity
        Place place = placeMapper.dtoToEntity(placeDto);
        place.setId(id);

        // save change
        place = placeRepository.save(place);
        return Optional.of(placeMapper.entityToDto(place));
    }

    @Override
    public Optional<PlaceDto> deletePlaceById(Long id) {
        // find and check exist
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Place.class.getSimpleName()));

        // delete
        placeRepository.deleteById(id);

        // return deleted place
        return Optional.of(placeMapper.entityToDto(place));
    }

    @Override
    public List<Place> searchPlace(String searchString) {
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)((\\w|[-])+?),");
        Matcher matcher = pattern.matcher(searchString + ",");
        List<PlaceSpecification> specificationList = new ArrayList<>();
        while (matcher.find()) {
            PlaceSpecification.SearchCriteria criteria = new PlaceSpecification.SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3));
            specificationList.add(new PlaceSpecification(criteria));
        }
        Optional<PlaceSpecification> optionalSpecification = specificationList.stream().reduce((a, b) -> (PlaceSpecification) a.and(b));

        if (optionalSpecification.isPresent())
            return placeRepository.findAll(optionalSpecification.get());
        else
            return placeRepository.findAll();
    }
}
