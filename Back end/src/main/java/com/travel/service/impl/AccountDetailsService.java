package com.travel.service.impl;

import com.travel.model.Account;
import com.travel.utils.FineGrained;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AccountDetailsService implements UserDetailsService {

    private final AccountServiceImpl service;

    private final FineGrained fineGrained;

    private static final Logger logger = LoggerFactory.getLogger(AccountDetailsService.class.getSimpleName());

    @Autowired
    public AccountDetailsService(AccountServiceImpl service, FineGrained fineGrained) {
        this.service = service;
        this.fineGrained = fineGrained;
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        logger.info("load user with username={}", userName);

        Account account = service.selectAccountByUserName(userName);
        if (account == null)
            throw new UsernameNotFoundException(String.format("Not found user with username=%s", userName));

        logger.info(account.toString());
        return new User(
                account.getUserName(), account.getPassword(), account.isEnabled(),
                true, true,
                true, fineGrained.getAuthorities(account.getRoles()));
    }


}
