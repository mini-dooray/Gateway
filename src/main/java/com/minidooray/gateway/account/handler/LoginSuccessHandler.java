package com.minidooray.gateway.account.handler;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RequiredArgsConstructor
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String sessionId = UUID.randomUUID().toString();

        Cookie cookie = new Cookie("SESSION", sessionId);
        cookie.setMaxAge(259200);

        response.addCookie(cookie);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        String authority = new ArrayList<>(userDetails.getAuthorities()).get(0).getAuthority();

        redisTemplate.opsForHash().put(sessionId, "username", username);
        redisTemplate.opsForHash().put(sessionId, "authority", authority);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
