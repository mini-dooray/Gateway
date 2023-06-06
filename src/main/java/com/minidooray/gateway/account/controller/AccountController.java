package com.minidooray.gateway.account.controller;

import com.minidooray.gateway.account.domain.RequestSignUpDto;
import com.minidooray.gateway.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/signup")
    public String getSignUpForm() {
        return "/signup.html";
    }

    @PostMapping("/signup/submit")
    public String signUp(RequestSignUpDto dto) {
        accountService.signUp(dto);

        return "redirect:/login";
    }
}
