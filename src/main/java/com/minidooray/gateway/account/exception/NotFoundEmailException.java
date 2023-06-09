package com.minidooray.gateway.account.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

public class NotFoundEmailException extends AuthenticationException {

    @Getter
    String email;

    public NotFoundEmailException(String msg, String email) {
        super(msg);
        this.email = email;
    }
}
