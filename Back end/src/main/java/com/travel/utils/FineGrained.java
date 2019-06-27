/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travel.utils;

import com.travel.model.Privilege;
import com.travel.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 *
 * @author 97lynk
 */
@Component
public class FineGrained {

    /**
     * Return Collection of GrantedAuthority from Roles
     * @param roles collection Roles
     * @return collection GrantedAuthority
     */
    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {

        return AuthorityUtils.createAuthorityList(
                roles.stream().map(Role::getPrivileges)
                        .flatMap(Collection::stream)
                        .map(Privilege::getName)
                        .toArray(String[]::new)
        );
    }
}
