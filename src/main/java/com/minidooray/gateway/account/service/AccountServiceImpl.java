package com.minidooray.gateway.account.service;

import com.minidooray.gateway.account.adapter.AccountAdapter;
import com.minidooray.gateway.account.domain.RequestSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountAdapter accountAdapter;

    @Override
    public void signUp(RequestSignUpDto dto) {
        accountAdapter.signUpAccount(dto);
    }
}
