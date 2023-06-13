package com.minidooray.gateway.task.validator;

import com.minidooray.gateway.account.validator.AbstractValidator;
import com.minidooray.gateway.task.adapter.TaskAdapter;
import com.minidooray.gateway.task.domain.request.RequestProjectCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@RequiredArgsConstructor
public class CheckProjectNameValidator extends AbstractValidator<RequestProjectCreateDto> {

    private final TaskAdapter taskAdapter;

    @Override
    public void doValidate(RequestProjectCreateDto dto, Errors errors) {

    }
}
