package com.travel.api.v1.assembler;

import com.travel.api.v1.dto.PlanDto;
import com.travel.api.v1.resource.PlanResource;
import com.travel.controller.PlanController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PlanResourceAssembler extends ResourceAssemblerSupport<PlanDto, PlanResource> {

    public PlanResourceAssembler() {
        super(PlanController.class, PlanResource.class);
    }

    @Override
    public PlanResource toResource(PlanDto planDto) {
        return new PlanResource(planDto);
    }
}
