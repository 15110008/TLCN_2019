package com.travel.api.v1.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.travel.api.v1.dto.TourDto;
import com.travel.controller.TourController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourResource extends Resource<TourDto> {

    public TourResource() {super(null); }

    public TourResource(TourDto tourDto) {
        super(tourDto);
        buildResource();
    }

    private void buildResource() {
        Link selfLink = linkTo(TourController.class)
                .slash(this.getContent().getId())
                .withSelfRel()
                .withTitle(String.format("Tour#%d", this.getContent().getId()));
        this.add(selfLink);
    }
}
