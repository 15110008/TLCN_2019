package com.travel.api.v1.mapper;

import com.travel.api.v1.dto.PlanDto;
import com.travel.model.Place;
import com.travel.model.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper
public interface PlanMapper {

    PlanMapper INSTANCE = Mappers.getMapper(PlanMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "startTime", target = "startTime"),
            @Mapping(source = "numberOfSlot", target = "numberOfSlot"),
            @Mapping(source = "numberOfReservedSlot", target = "numberOfReservedSlot"),
            @Mapping(source = "adultPrice", target = "adultPrice"),
            @Mapping(source = "childPrice", target = "childPrice"),
            @Mapping(source = "tour.id", target = "tourId")
    })
    PlanDto entityToDto(Plan plan);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "startTime", target = "startTime"),
            @Mapping(source = "numberOfSlot", target = "numberOfSlot"),
            @Mapping(source = "numberOfReservedSlot", target = "numberOfReservedSlot"),
            @Mapping(source = "adultPrice", target = "adultPrice"),
            @Mapping(source = "childPrice", target = "childPrice")
    })
    Plan dtoToEntity(PlanDto planDto);

    default PlanDto entityToDtoWithTourId(Plan plan) {
        PlanDto planDto = entityToDto(plan);
        planDto.setPlaceIds(plan.getPlaces().stream().map(Place::getId).collect(Collectors.toList()));
        planDto.setTourId(plan.getTour().getId());
        return planDto;
    }
}
