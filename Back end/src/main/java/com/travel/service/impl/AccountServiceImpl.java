/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travel.service.impl;

import com.travel.model.Account;
import com.travel.model.Privilege;
import com.travel.model.Role;
import com.travel.repository.AccountRepository;
import com.travel.repository.PrivilegeRepository;
import com.travel.repository.RoleRepository;
import com.travel.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }
    
    @Override
    public Account selectAccountByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }

    @Override
    public Role selectRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Privilege selectPrivilegeByName(String name) {
        return privilegeRepository.findByName(name);
    }

    @Override
    public Account insertAccount(Account account) {
        return accountRepository.save(account);
    }


}
