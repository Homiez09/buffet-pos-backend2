package com.buffetpos.backend.components;

import com.buffetpos.backend.repositories.MenuRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AccessCodeFilter extends OncePerRequestFilter {

    private final MenuRepository menuRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getRequestURI();
        String accessCode = request.getHeader("AccessCode");

        if (path.startsWith("/api/customer/")) {
            if (accessCode == null || accessCode.isBlank()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("AccessCode is required.");
                return;
            }

            try {
                // todo: เปลี่ยนจาก menu เป็น table เพราะอันนี้ใช้ทดสอบเฉยๆ
                Long menuId = Long.parseLong(accessCode);

                boolean exists = menuRepository.existsById(menuId);
                if (!exists) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Invalid AccessCode.");
                    return;
                }

            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("AccessCode must be a number.");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}