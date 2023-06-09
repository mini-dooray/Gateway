package com.minidooray.gateway.account.controller;

import com.minidooray.gateway.account.domain.RequestSignUpDto;
import com.minidooray.gateway.account.service.AccountService;
import com.minidooray.gateway.account.validator.CheckEmailValidator;
import com.minidooray.gateway.account.validator.CheckIdValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final PasswordEncoder passwordEncoder;

    private final CheckIdValidator checkIdValidator;
    private final CheckEmailValidator checkEmailValidator;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkIdValidator);
        binder.addValidators(checkEmailValidator);
    }

    @GetMapping("/signup")
    public String getSignUpForm() {
        return "/signup.html";
    }

    @PostMapping("/signup/submit")
    public String signUp(@Valid RequestSignUpDto dto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("account", dto);

            Map<String, String> validatorResult = accountService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "/signup.html";
        }
        String password = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(password);

        accountService.signUp(dto);

        return "redirect:/login";
    }
}
