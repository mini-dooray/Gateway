package com.minidooray.gateway.account.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Account {

    private Integer accountSeq;

    private String accountId;

    private String password;

    private String name;

    private String email;

    private String phoneNumber;

    private LocalDate lastAccessDate;

    private Integer status;
}
