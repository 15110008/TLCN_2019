package com.travel.service;

import com.travel.api.v1.dto.PlanDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PlanService {

   Optional<PlanDto> getPlanById(Long id);

   Page<PlanDto> getPlans(Pageable pageable);

   Page<PlanDto> getPlansByPlaceId(Long placeId, Pageable pageable);

   Optional<PlanDto> addPlan(PlanDto newPlan);

   Optional<PlanDto> editPlanById(Long id, PlanDto planDto);

   Optional<PlanDto> deletePlanById(Long id);
}
