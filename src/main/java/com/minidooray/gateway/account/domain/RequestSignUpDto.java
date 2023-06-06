package com.minidooray.gateway.account.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RequestSignUpDto {

    @NotNull
    private String accountId;

    @NotNull
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;

    @NotNull
    private String name;

    @NotNull
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
    private String password;

    private String phoneNumber;
}
