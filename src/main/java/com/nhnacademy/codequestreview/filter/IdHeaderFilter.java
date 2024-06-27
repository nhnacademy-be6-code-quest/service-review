package com.nhnacademy.codequestreview.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class IdHeaderFilter extends OncePerRequestFilter {

    private final String requiredPath;
    private final String requiredMethod;
    private final AntPathMatcher pathMatcher;

    public IdHeaderFilter(String requiredPath, String requiredMethod) {
        this.requiredPath = requiredPath;
        this.requiredMethod = requiredMethod;
        this.pathMatcher = new AntPathMatcher();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        log.info("header filter start");
        if (pathMatcher.match(requiredPath, request.getRequestURI()) && request.getMethod()
            .equalsIgnoreCase(requiredMethod)) {
            try {
                Long.valueOf(request.getHeader("X-User-Id"));
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "id header is missing or invalid");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}