package com.travel.api.v1.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AccountDto implements Serializable {

    private String userName;

    private String fullName;

    private boolean gender;

    private boolean enabled;
}
