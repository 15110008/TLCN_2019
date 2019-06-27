package com.travel.controller;

import com.travel.api.v1.dto.BookingDto;
import com.travel.api.v1.resource.BookingDetailResource;
import com.travel.api.v1.resource.BookingResource;
import com.travel.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/bookings", produces = "application/hal+json")
@PreAuthorize("#oauth2.hasAnyScope('read')")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private final Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());

    @GetMapping
    public Resources<BookingResource> getCart(OAuth2Authentication auth) {
        logger.info("{}", auth);
        List<BookingResource> bookingResources = bookingService.getBookingsInCartByUserName(auth.getName())
                .stream().map(BookingResource::new).collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(BookingController.class).getCart(auth))
                .withSelfRel().withTitle(String.format("Cart of %s", auth.getName()));

        return new Resources<>(bookingResources, selfLink);
    }

    @GetMapping("/{id}/details")
    public Resources<BookingDetailResource> getDetails(@PathVariable("id") Long bookId) {
        List<BookingDetailResource> detailResources = bookingService.getDetailsByBookingId(bookId)
                .stream().map(BookingDetailResource::new)
                .collect(Collectors.toList());

        Link selfLink = linkTo(methodOn(BookingController.class).getDetails(bookId))
                .withSelfRel()
                .withTitle(String.format("List details of Book#%d", bookId));
        Resources<BookingDetailResource> resources = new Resources<>(detailResources, selfLink);
        return resources;
    }

    @GetMapping("/{id}")
    public BookingResource getBooking(@PathVariable("id") Long id) {
        return new BookingResource(bookingService.getABookingById(id).get());
    }

    @PostMapping
    public BookingResource bookingTour(@RequestBody BookingDto bookingDto, OAuth2Authentication auth) {
        bookingDto.setUserName(auth.getName());
        Long bookingId = bookingService.bookingATour(bookingDto, bookingDto.getDetails()).get().getId();
        return getBooking(bookingId);
    }

    @DeleteMapping("/{id}")
    public BookingDto deleteBooking(@PathVariable("id") Long id) {
        return bookingService.deleteABookingById(id).get();
    }


}
