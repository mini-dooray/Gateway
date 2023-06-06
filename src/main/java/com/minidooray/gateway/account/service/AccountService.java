package com.minidooray.gateway.account.service;

import com.minidooray.gateway.account.domain.RequestSignUpDto;

public interface AccountService {
    void signUp(RequestSignUpDto dto);
}
