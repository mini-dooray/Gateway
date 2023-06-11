package com.minidooray.gateway.account.service;

import com.minidooray.gateway.account.adapter.AccountAdapter;
import com.minidooray.gateway.account.domain.Account;
import com.minidooray.gateway.account.domain.RequestSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountAdapter accountAdapter;

    @Override
    public void signUp(RequestSignUpDto dto) {
        accountAdapter.signUpAccount(dto);
    }

    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    @Override
    public Account getAccountById(String id) {
        return accountAdapter.getAccountById(id);
    }

    @Override
    public boolean checkIdDuplication(String id) {
        return accountAdapter.isId(id);
    }

    @Override
    public boolean checkEmailDuplication(String email) {
        return accountAdapter.isEmail(email);
    }
}
