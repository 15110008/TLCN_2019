package com.travel.service;

import com.travel.api.v1.dto.BookingDetailDto;
import com.travel.api.v1.dto.BookingDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    Optional<BookingDto> bookingATour(BookingDto bookingDto, List<BookingDetailDto> detailList);

    Optional<BookingDto> getABookingById(Long bookingId);

    Optional<BookingDto> deleteABookingById(Long bookingId);

    List<BookingDetailDto> getDetailsByBookingId(Long bookingId);

    List<BookingDto> getBookingsInCartByUserName(String username);
}
