package com.bolehunt.security.filter;

import com.bolehunt.exception.AppException;
import com.bolehunt.security.JwtTokenProvider;
import com.bolehunt.domain.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bolehunt.constant.SecurityConstants.HEADER_STRING;
import static com.bolehunt.constant.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter() {

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        LoginRequest loginRequest;
        try {
            loginRequest = new ObjectMapper().readValue(req.getInputStream(), LoginRequest.class);
        } catch (IOException e) {
            throw new AppException("Parse httpRequest InputStream exception", e);
        }

        LOGGER.info("attemptAuthentication, loginRequest = {}", loginRequest);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
                ));

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = jwtTokenProvider.generateToken(auth);

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }
}
