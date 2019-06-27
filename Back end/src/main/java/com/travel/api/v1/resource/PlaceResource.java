package com.travel.api.v1.resource;

import com.travel.api.v1.dto.PlaceDto;
import com.travel.controller.PlanController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Getter
@Setter
public class PlaceResource extends Resource<PlaceDto> {


    public PlaceResource() {super(null); }

    public PlaceResource(PlaceDto placeDto) {
        super(placeDto);
        buildResource();
    }

    private void buildResource() {
        Link selfLink = linkTo(PlanController.class)
                .slash(this.getContent().getId())
                .withSelfRel()
                .withTitle(String.format("Place#%d", this.getContent().getId()));
        this.add(selfLink);
    }

}
