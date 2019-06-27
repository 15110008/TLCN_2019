package com.travel.api.v1.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.api.v1.dto.PlaceDto;
import com.travel.api.v1.dto.PlanDto;
import com.travel.api.v1.dto.TourDto;
import com.travel.controller.PlanController;
import com.travel.controller.TourController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanResource extends Resource<PlanDto> {

    private TourDto tour;

    private List<PlaceDto> places;

    public PlanResource() {super(null); }

    public PlanResource(PlanDto planDto) {
        super(planDto);
        buildResource();
    }

    private void buildResource() {
        Link selfLink = linkTo(PlanController.class)
                .slash(this.getContent().getId())
                .withSelfRel()
                .withTitle(String.format("Plan#%d", this.getContent().getId()));
        Link tourLink = linkTo(TourController.class).slash(this.getContent().getTourId())
                .withRel("tour");

        Link placesLink = linkTo(methodOn(PlanController.class).getAllPlacesOfPlan(getContent().getId()))
                .withRel("places");
        this.add(selfLink, tourLink, placesLink);
    }

}
