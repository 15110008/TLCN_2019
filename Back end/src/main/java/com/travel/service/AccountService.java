/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travel.service;

import com.travel.model.Account;
import com.travel.model.Privilege;
import com.travel.model.Role;

/**
 *
 * @author 97lynk
 */
public interface AccountService {

    Account selectAccountByUserName(String userName);

    Role selectRoleByName(String name);

    Privilege selectPrivilegeByName(String name);

    Account insertAccount(Account account);

}
