package com.travel.api.v1.mapper;

import com.travel.api.v1.dto.BookingDto;
import com.travel.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    @Mappings({@Mapping(source = "plan.id", target = "planId")})
    BookingDto entityToDto(Booking booking);

    @Mappings({})
    Booking dtoToEntity(BookingDto bookingDto);

}
