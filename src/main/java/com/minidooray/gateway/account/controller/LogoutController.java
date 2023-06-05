package com.minidooray.gateway.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class LogoutController {

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("SESSION"))
                .findFirst()
                .orElse(null);

        if(Objects.nonNull(cookie)) {
            String sessionId = cookie.getValue();

            cookie.setMaxAge(0);
            response.addCookie(cookie);

            redisTemplate.opsForHash().delete(sessionId, "username", "authority");
        }

        return "redirect:/login";
    }
}
