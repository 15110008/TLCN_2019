package com.travel.api.v1.resource;

import com.travel.api.v1.dto.BookingDetailDto;
import com.travel.controller.BookingController;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Setter
@Getter
public class BookingDetailResource extends Resource<BookingDetailDto> {


    public BookingDetailResource() {super(null); }

    public BookingDetailResource(BookingDetailDto detailDto) {
        super(detailDto);
        buildResource();
    }

    private void buildResource() {
        Link selfLink = linkTo(BookingController.class)
                .slash(this.getContent().getId())
                .withSelfRel()
                .withTitle(String.format("BookingDetail#%d", this.getContent().getId()));
        this.add(selfLink);
    }

}
