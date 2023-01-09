package com.example.collecter.global.error;

import com.example.collecter.global.error.exception.CollectorException;
import com.example.collecter.global.error.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (CollectorException e) {
            setErrorCode(response, e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            setErrorCode(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void setErrorCode(HttpServletResponse response, ErrorCode errorCode) throws IOException {

        ErrorResponse errorResponse = new ErrorResponse(
                errorCode.getStatus(),
                errorCode.getMessage()
        );

        response.setStatus(errorCode.getStatus());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
