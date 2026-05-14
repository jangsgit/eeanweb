package com.dae.eean.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        String requestedWith = request.getHeader("X-Requested-With");

        if ("XMLHttpRequest".equals(requestedWith)) {
            // Ajax 요청이면 401 반환 (프론트에서 처리)
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } else {
            // 일반 페이지 요청이면 로그인 페이지로 리다이렉트
            response.sendRedirect("/members/loginForm");
        }
    }
}