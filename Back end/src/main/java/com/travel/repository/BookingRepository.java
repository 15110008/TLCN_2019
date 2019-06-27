package com.travel.repository;

import com.travel.contant.ProcessType;
import com.travel.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByProcessTypeAndAccount_UserName(ProcessType processType, String username);
}
