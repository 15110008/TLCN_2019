package com.travel.api.v1.mapper;

import com.travel.api.v1.dto.PlaceDto;
import com.travel.model.Place;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    @Mappings({})
    PlaceDto entityToDto(Place place);

    @Mappings({})
    Place dtoToEntity(PlaceDto placeDto);

}
