package com.minidooray.gateway.account.validator;

import com.minidooray.gateway.account.adapter.AccountAdapter;
import com.minidooray.gateway.account.domain.Account;
import com.minidooray.gateway.account.domain.RequestSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckEmailValidator extends AbstractValidator<RequestSignUpDto> {

    private final AccountAdapter accountAdapter;

    @Override
    public void doValidate(RequestSignUpDto dto, Errors errors) {
        if(accountAdapter.isEmail(dto.getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용 중인 이메일 입니다.");
        }
    }
}
