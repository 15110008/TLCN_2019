package com.travel.api.v1.mapper;

import com.travel.api.v1.dto.TourDto;
import com.travel.model.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TourMapper {

    TourMapper INSTANCE = Mappers.getMapper(TourMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "fileContentUrl", target = "fileContentUrl"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "numberOfDate", target = "numberOfDate"),
            @Mapping(source = "numberOfNight", target = "numberOfNight")
    })
    TourDto entityToDto(Tour tour);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "url", target = "url"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "numberOfDate", target = "numberOfDate"),
            @Mapping(source = "numberOfNight", target = "numberOfNight")
    })
    Tour dtoToEntity(TourDto tourDto);

}
