package com.travel.api.v1.assembler;

import com.travel.api.v1.dto.TourDto;
import com.travel.api.v1.resource.TourResource;
import com.travel.controller.TourController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TourResourceAssembler extends ResourceAssemblerSupport<TourDto, TourResource> {

    public TourResourceAssembler() {
        super(TourController.class, TourResource.class);
    }

    @Override
    public TourResource toResource(TourDto tourDto) {
        return new TourResource(tourDto);
    }
}
