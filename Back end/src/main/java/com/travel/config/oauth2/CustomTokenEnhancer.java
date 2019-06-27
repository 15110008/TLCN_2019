package com.travel.config.oauth2;

import com.travel.api.v1.dto.AccountDto;
import com.travel.api.v1.mapper.AccountMapper;
import com.travel.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Autowired
    private AccountRepository accountRepository;

    private AccountMapper mapper = AccountMapper.INSTANCE;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication auth) {
        final Map<String, Object> additionalInfo = new HashMap<>();
        AccountDto accountDto = mapper.entityToDto(accountRepository.findByUserName(auth.getName()));
        additionalInfo.put("account", accountDto);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
