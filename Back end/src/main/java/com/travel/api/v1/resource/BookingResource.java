package com.travel.api.v1.resource;

import com.travel.api.v1.dto.BookingDto;
import com.travel.controller.BookingController;
import com.travel.controller.PlanController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import java.util.Arrays;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Setter
@Getter
public class BookingResource extends Resource<BookingDto> {


    public BookingResource() {super(null); }

    public BookingResource(BookingDto bookingDto) {
        super(bookingDto);
        buildResource();
    }

    private void buildResource() {
        Link selfLink = linkTo(BookingController.class)
                .slash(this.getContent().getId())
                .withSelfRel()
                .withTitle(String.format("Booking#%d", this.getContent().getId()));

        Link planLink = linkTo(methodOn(PlanController.class).getPlan(this.getContent().getPlanId(), Arrays.asList("tour")))
                .withRel("plan")
                .withTitle("Plan of Booking");

        Link detailsLink =  linkTo(methodOn(BookingController.class).
                getDetails(this.getContent().getId()))
                .withRel("details")
                .withTitle(String.format("List details of Booking#%d", this.getContent().getId()));
        this.add(selfLink, planLink, detailsLink);
    }

}
