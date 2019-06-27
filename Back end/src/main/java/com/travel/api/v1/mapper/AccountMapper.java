package com.travel.api.v1.mapper;

import com.travel.api.v1.dto.AccountDto;
import com.travel.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({})
    AccountDto entityToDto(Account account);

}
