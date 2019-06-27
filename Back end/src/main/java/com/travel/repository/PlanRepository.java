package com.travel.repository;

import com.travel.model.Place;
import com.travel.model.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Page<Plan> findAllByPlacesIs(Place place, Pageable pageable);

    Page<Plan> findDistinctByPlacesIn(List<Place> places, Pageable pageable);

    @Query("select p from Plan p where p.tour.enable = true")
    Page<Plan> findAll(Pageable pageable);
}
