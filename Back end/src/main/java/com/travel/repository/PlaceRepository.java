package com.travel.repository;

import com.travel.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place> {

    Set<Place> findAllByIdIsIn(Collection<Long> ids);

    @Query("SELECT parent FROM Place child, Place parent " +
            "WHERE child.id = ?1 and child.parentId <> null and child.parentId = parent.id")
    Optional<Place> getParentByChildId(Long id);

    @Query("SELECT p FROM Place p WHERE p.id = 0")
    Optional<Place> getVietNamPlace();

    @Query(value = "SELECT plans_places.place_id, COUNT(plans_places.plan_id) FROM plans_places GROUP BY plans_places.place_id",
            nativeQuery = true)
    List<Object[]> getPlaceIdAndCountPlans();
}
