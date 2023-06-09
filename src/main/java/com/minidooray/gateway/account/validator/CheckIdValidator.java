package com.minidooray.gateway.account.validator;

import com.minidooray.gateway.account.adapter.AccountAdapter;
import com.minidooray.gateway.account.domain.Account;
import com.minidooray.gateway.account.domain.RequestSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckIdValidator extends AbstractValidator<RequestSignUpDto> {

    private final AccountAdapter accountAdapter;

    @Override
    public void doValidate(RequestSignUpDto dto, Errors errors) {
        if(accountAdapter.isId(dto.getAccountId())) {
            errors.rejectValue("accountId", "아이디 중복 오류", "이미 사용 중인 아이디입니다.");
        }
    }
}
