package com.travel.repository;

import com.travel.model.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Long> {

    List<BookingDetail> findAllByBookingId(Long bookingId);

    void deleteAllByBookingId(Long bookingId);
}
