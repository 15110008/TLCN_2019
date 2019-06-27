package com.travel.api.v1.assembler;

import com.travel.api.v1.dto.PlanDto;
import com.travel.api.v1.dto.TourDto;
import com.travel.api.v1.resource.PlanResource;
import com.travel.controller.PlanController;
import com.travel.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PlanResourceAssembler2 extends ResourceAssemblerSupport<PlanDto, PlanResource> {

    @Autowired
    private TourService tourService;

    public PlanResourceAssembler2() {
        super(PlanController.class, PlanResource.class);
    }

    @Override
    public PlanResource toResource(PlanDto planDto) {
        return expandData(planDto);
    }

    private PlanResource expandData(PlanDto planDto) {
        PlanResource planResource = new PlanResource(planDto);
        TourDto tourDto = tourService.getTourById(planDto.getTourId()).get();
        planResource.setTour(tourDto);
        return planResource;
    }
}
