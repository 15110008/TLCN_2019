package com.travel.api.v1.mapper;

import com.travel.api.v1.dto.BookingDetailDto;
import com.travel.model.BookingDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingDetailMapper {

    BookingDetailMapper INSTANCE = Mappers.getMapper(BookingDetailMapper.class);

    @Mappings({@Mapping(source = "booking.id", target = "bookingId")})
    BookingDetailDto entityToDto(BookingDetail bookingDetail);

    @Mappings({})
    BookingDetail dtoToEntity(BookingDetailDto bookingDetailDto);

}
