/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author 97lynk
 */
@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String fullName;

    private boolean gender;

    private String lastName;

    @JsonIgnore
    private String password;

    private boolean enabled;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = @JoinColumn(
                    name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "account")
    private Set<Booking> bookings = new HashSet<>();

    //constructors
    public Account() {
        this.enabled = false;
    }

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.enabled = false;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account acc = (Account) obj;
        if (!userName.equals(acc.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Account [id=").append(id)
                .append(", userName=").append(userName)
                .append(", enabled=").append(enabled)
                .append(", roles=").append(roles).append("]");
        return builder.toString();
    }
}
