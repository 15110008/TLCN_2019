package com.travel.controller;

import com.travel.api.v1.dto.AccountDto;
import com.travel.api.v1.mapper.AccountMapper;
import com.travel.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = "application/hal+json")
@PreAuthorize("#oauth2.hasAnyScope('read')")
public class AccountController {

    private final AccountServiceImpl oAuth2Service;

    private AccountMapper mapper = AccountMapper.INSTANCE;

    @Autowired
    public AccountController(AccountServiceImpl oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    @GetMapping
    public AccountDto getProfile(OAuth2Authentication auth) {
        return mapper.entityToDto(oAuth2Service.selectAccountByUserName(auth.getName()));
    }

}
