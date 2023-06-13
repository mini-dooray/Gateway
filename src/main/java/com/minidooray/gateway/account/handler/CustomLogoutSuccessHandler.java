package com.minidooray.gateway.account.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisTemplate redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Cookie cookie = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("SESSION"))
                .findFirst()
                .orElse(null);

        if (Objects.nonNull(cookie)) {
            String sessionId = cookie.getValue();

            cookie.setMaxAge(0);
            response.addCookie(cookie);

            redisTemplate.delete(sessionId);
        }

        response.sendRedirect("/");
    }
}
