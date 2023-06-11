package com.minidooray.gateway.account.service;

import com.minidooray.gateway.account.domain.Account;
import com.minidooray.gateway.account.domain.RequestSignUpDto;
import org.springframework.validation.Errors;

import java.util.Map;

public interface AccountService {
    void signUp(RequestSignUpDto dto);

    boolean checkIdDuplication(String id);

    boolean checkEmailDuplication(String email);

    Map<String, String> validateHandling(Errors errors);

    Account getAccountById(String id);
}
