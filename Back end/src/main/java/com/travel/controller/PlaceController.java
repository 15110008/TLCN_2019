package com.travel.controller;

import com.travel.api.v1.dto.PlaceDto;
import com.travel.api.v1.dto.PlanDto;
import com.travel.api.v1.resource.PlaceResource;
import com.travel.api.v1.resource.PlanResource;
import com.travel.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/places", produces = "application/hal+json")
public class PlaceController {

    private final PlaceService placeService;

    private final  PlanController planController;

    @Autowired
    public PlaceController(PlaceService placeService, PlanController planController) {
        this.placeService = placeService;
        this.planController = planController;
    }

    /**
     * API for Place entity
     */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Resources<PlaceResource> getAllPlaces() {
        List<PlaceResource> placeResources = placeService.getPlaces("").stream()
                    .map(PlaceResource::new).collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(PlaceController.class).getAllPlaces()).withSelfRel().withTitle("List all Places");
        Resources<PlaceResource> resources = new Resources<>(placeResources, selfLink);
        return resources;
    }

    @GetMapping("/{id}")
    public PlaceResource getPlace(@PathVariable("id") Long id) {
        PlaceDto placeDto = placeService.getPlaceById(id).get();
        return new PlaceResource(placeDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlaceResource createPlace(@RequestBody PlaceDto placeDto) {
        PlaceDto insertedPlace = placeService.addPlace(placeDto).get();
        return new PlaceResource(insertedPlace);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaceResource updatePlace(@PathVariable long id, @RequestBody PlaceDto placeDto) {
        PlaceDto updatedPlace = placeService.editPlaceById(id, placeDto).get();
        PlaceResource placeResource = new PlaceResource(updatedPlace);
        return placeResource;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlaceDto deletePlace(@PathVariable long id) {
        PlaceDto deletedPlace = placeService.deletePlaceById(id).get();
        return deletedPlace;
    }


    /**
     * API FOR SEARCHING
     */

    @GetMapping("/{id}/plans")
    public Resources<PlanResource> getAllPlansInSpecificPlace(@PathVariable("id") Long placeId,
                                                              @PageableDefault(page = 0, size = 10)Pageable pageable,
                                                              PagedResourcesAssembler<PlanDto> assembler) {
        return planController.getAllPlans(placeId, Collections.emptyList(), pageable, assembler);
    }
}
