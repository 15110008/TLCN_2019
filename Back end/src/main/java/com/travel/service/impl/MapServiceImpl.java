package com.travel.service.impl;

import com.travel.api.v1.dto.PlaceDto;
import com.travel.api.v1.dto.PlanDto;
import com.travel.api.v1.mapper.PlaceMapper;
import com.travel.api.v1.mapper.PlanMapper;
import com.travel.model.Place;
import com.travel.repository.PlaceRepository;
import com.travel.repository.PlanRepository;
import com.travel.service.MapService;
import com.travel.service.PlaceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MapServiceImpl implements MapService {

    private final PlaceRepository placeRepository;

    private final PlanRepository planRepository;

    private final PlaceService placeService;

    private final PlaceMapper placeMapper = PlaceMapper.INSTANCE;

    private final PlanMapper planMapper = PlanMapper.INSTANCE;

    public MapServiceImpl(PlaceRepository placeRepository, PlanRepository planRepository, PlaceService placeService) {
        this.placeRepository = placeRepository;
        this.planRepository = planRepository;
        this.placeService = placeService;
    }

    /**
     * Get all place contains plans
     * @return
     */
    @Override
    public List<PlaceDto> getPlacesContainsPlan() {
        // place default
        final Place vn = new Place();
        vn.setId(99l);
        vn.setImageUrl("https://www.kfw-entwicklungsbank.de/Bilder/Bilderordner/Karten/Vietnam-Karte-EN_Responsive_1080x608.jpg");
        vn.setName("Việt Nam");
        vn.setUrl("viet-nam");
        vn.setLatitude(15.6069575);
        vn.setLongitude(96.8603511);

        // collection a map with a place id as key and number of plans as value
        Map<Long, Long> placeIdAndNoOfPlans = placeRepository.getPlaceIdAndCountPlans().stream().collect(
                Collectors.toMap(
                        entry -> Long.valueOf(entry[0].toString()),
                        entry -> Long.valueOf(entry[1].toString()))
        );

        return placeRepository.findAllByIdIsIn(placeIdAndNoOfPlans.keySet()).stream()
                .map(placeMapper::entityToDto)
                .map(placeDto -> {
                    // expand parent place
                    Optional<Place> parentPlaceOptional = placeRepository.getParentByChildId(placeDto.getId());
                    Place parentPlace = parentPlaceOptional.orElseGet(() -> placeRepository.getVietNamPlace().orElse(vn));
                    placeDto.setParent(placeMapper.entityToDto(parentPlace));

                    // add number of plans
                    placeDto.setCountPlan(placeIdAndNoOfPlans.getOrDefault(placeDto.getId(), 0l));
                    return placeDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Page<PlanDto> getPlansWithUrlOfPlaces(String url, Pageable pageable) {
        List<Place> places = placeService.searchPlace(String.format("%s:%s", "url", url)); // search place by url field

        return planRepository.findDistinctByPlacesIn(places, pageable) // get all plan with found places, not duplicate
                .map(planMapper::entityToDtoWithTourId);
//                .filter(distinctByKey(p -> p.getId()))
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
