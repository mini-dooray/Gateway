package com.minidooray.gateway.account.handler;

import com.minidooray.gateway.account.exception.NotFoundEmailException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof NotFoundEmailException) {
            HttpSession session = request.getSession();
            session.setAttribute("email", ((NotFoundEmailException) exception).getEmail());

            response.sendRedirect("/signup");
        }
    }
}
