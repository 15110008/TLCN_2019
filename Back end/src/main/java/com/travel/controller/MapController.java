package com.travel.controller;

import com.travel.api.v1.assembler.PlanResourceAssembler2;
import com.travel.api.v1.dto.PlanDto;
import com.travel.api.v1.resource.PlaceResource;
import com.travel.api.v1.resource.PlanResource;
import com.travel.service.MapService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/maps", produces = "application/hal+json")
public class MapController {

    private final MapService mapService;

    private final PlanController planController;

    private final PlanResourceAssembler2 planResourceAssembler;

    public MapController(MapService mapService, PlanController planController, PlanResourceAssembler2 planResourceAssembler) {
        this.mapService = mapService;
        this.planController = planController;
        this.planResourceAssembler = planResourceAssembler;
    }

    @GetMapping("/places")
    public Resources<PlaceResource> getAllPlacesHasPlan() {
        List<PlaceResource> placeResources = mapService.getPlacesContainsPlan().stream().map(PlaceResource::new)
                .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(MapController.class).getAllPlacesHasPlan())
                .withSelfRel().withTitle("List all Places contains plan");
        return new Resources<>(placeResources, selfLink);
    }

    @GetMapping("/plans")
    public PagedResources<PlanResource> getAllPlanBySearchPlaceUrl(@RequestParam(value = "search", required = false) String search,
                                                                   @PageableDefault(page = 0, size = 10) Pageable pageable,
                                                                   PagedResourcesAssembler<PlanDto> assembler) {
        Page<PlanDto> planDtos = mapService.getPlansWithUrlOfPlaces(search, pageable);
//                .map(planDto -> planController.expandData(planDto, Arrays.asList("tour")));

        return assembler.toResource(planDtos, planResourceAssembler);
    }
}
