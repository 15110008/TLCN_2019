package com.travel.controller;

import com.travel.api.v1.assembler.PlanResourceAssembler;
import com.travel.api.v1.dto.PlaceDto;
import com.travel.api.v1.dto.PlanDto;
import com.travel.api.v1.dto.TourDto;
import com.travel.api.v1.resource.PlaceResource;
import com.travel.api.v1.resource.PlanResource;
import com.travel.service.PlaceService;
import com.travel.service.PlanService;
import com.travel.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/plans", produces = "application/hal+json")
public class PlanController {

    private final PlanService planService;

    private final TourService tourService;

    private final PlaceService placeService;

    private final PlanResourceAssembler planResourceAssembler;

    @Autowired
    public PlanController(PlanService planService, TourService tourService, PlaceService placeService, PlanResourceAssembler planResourceAssembler) {
        this.planService = planService;
        this.tourService = tourService;
        this.placeService = placeService;
        this.planResourceAssembler = planResourceAssembler;
    }


    /**
     * API FOR PLAN ENTITY
     */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PagedResources<PlanResource> getAllPlans(@RequestParam(value = "placeId", required = false) Long placeId,
                                                    @RequestParam(name = "_expand", defaultValue = "[]", required = false) List<String> expands,
                                                    @PageableDefault(page = 0, size = 10) Pageable pageable,
                                                    PagedResourcesAssembler<PlanDto> assembler) {
        PagedResources<PlanResource> planResources;
        // search by placeID
        if (placeId != null)
            planResources = assembler.toResource(planService.getPlansByPlaceId(placeId, pageable), planResourceAssembler);
        else
            planResources = assembler.toResource(planService.getPlans(pageable), planResourceAssembler);

        planResources.forEach(planResource -> expandData(planResource, expands));
        // TODO expand tour and places
        return planResources;
    }

    @GetMapping("/{id}")
    public PlanResource getPlan(@PathVariable long id, @RequestParam(name = "_expand", defaultValue = "[]", required = false) List<String> expands) {
        PlanDto planDto = planService.getPlanById(id).get();
        return expandData(planDto, expands);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlanResource createPlan(@RequestBody PlanDto planDto) {
        PlanDto insertedPlan = planService.addPlan(planDto).get();
        return new PlanResource(insertedPlan);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlanResource updatePlan(@PathVariable long id, @RequestBody PlanDto planDto) {
        PlanDto updatedPlan = planService.editPlanById(id, planDto).get();
        PlanResource planResource = new PlanResource(updatedPlan);
        return planResource;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlanDto deletePlan(@PathVariable long id) {
        PlanDto deletedPlan = planService.deletePlanById(id).get();
        return deletedPlan;
    }


    /**
     * API FOR SEARCHING
     */

    @GetMapping("/{id}/places")
    public Resources<PlaceResource> getAllPlacesOfPlan(@PathVariable("id") Long planId) {
        List<PlaceResource> placeResources = placeService.getPlacesByPlanId(planId).stream()
                .map(PlaceResource::new).collect(Collectors.toList());
        Link selfLink = linkTo(methodOn(PlanController.class).getAllPlacesOfPlan(planId))
                .withSelfRel().withTitle(String.format("List all Places of Plan#%d", planId));
        Resources<PlaceResource> resources = new Resources<>(placeResources, selfLink);
        return resources;
    }

    /**
     * Extra method
     */

    public PlanResource expandData(PlanDto planDto, List<String> expandTableNames) {
        PlanResource planResource = new PlanResource(planDto);
        if (expandTableNames.contains("tour")) {
            TourDto tourDto = tourService.getTourById(planDto.getTourId()).get();
            planResource.setTour(tourDto);
        }
        if (expandTableNames.contains("places")) {
            List<PlaceDto> places = placeService.getPlacesByPlanId(planDto.getId());
            planResource.setPlaces(places);
        }
        return planResource;
    }

    public PlanResource expandData(PlanResource planResource, List<String> expandTableNames) {
        if (expandTableNames.contains("tour")) {
            TourDto tourDto = tourService.getTourById(planResource.getContent().getTourId()).get();
            planResource.setTour(tourDto);
        }
        if (expandTableNames.contains("places")) {
            List<PlaceDto> places = placeService.getPlacesByPlanId(planResource.getContent().getId());
            planResource.setPlaces(places);
        }
        return planResource;
    }
}
