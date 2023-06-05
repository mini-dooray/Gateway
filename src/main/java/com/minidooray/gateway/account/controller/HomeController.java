package com.minidooray.gateway.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/")
    public String home(@CookieValue(name = "SESSION", required = false) String sessionId, Model model) {
        if (Objects.isNull(sessionId)) {
            return "redirect:/login";
        } else {
            String username = (String) redisTemplate.opsForHash().get(sessionId, "username");
            String authority = (String) redisTemplate.opsForHash().get(sessionId, "authority");

            model.addAttribute("username", username);
            model.addAttribute("authority", authority);

            return "home";
        }
    }
}
