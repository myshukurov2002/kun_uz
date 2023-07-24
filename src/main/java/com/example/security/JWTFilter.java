package com.example.security;import com.example.dto.JwtDTO;import com.example.exception.UnAuthorizedException;import com.example.util.JWTUtil;import io.jsonwebtoken.JwtException;import jakarta.servlet.FilterChain;import jakarta.servlet.ServletException;import jakarta.servlet.ServletRequest;import jakarta.servlet.ServletResponse;import jakarta.servlet.http.HttpServletRequest;import jakarta.servlet.http.HttpServletResponse;import org.springframework.stereotype.Component;import org.springframework.web.filter.GenericFilterBean;import java.io.IOException;@Componentpublic class JWTFilter extends GenericFilterBean {    @Override    public void doFilter(ServletRequest servletRequest,                         ServletResponse servletResponse,                         FilterChain filterChain) throws IOException, ServletException {        final HttpServletRequest request = (HttpServletRequest) servletRequest;        final HttpServletResponse response = (HttpServletResponse) servletResponse;        final String authHeader = request.getHeader("Authorization");        if (authHeader == null || !authHeader.startsWith("Bearer")) {            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);            response.setHeader("Message", "token not found!");            return;        }        String token = authHeader.substring(7);        JwtDTO jwtDTO;        try {            jwtDTO = JWTUtil.decode(token);        } catch (UnAuthorizedException e) {            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);            response.setHeader("Message", "token not valid!");            return;        }        request.setAttribute("id", jwtDTO.getId());        request.setAttribute("role", jwtDTO.getRole());        filterChain.doFilter(request, response);    }}