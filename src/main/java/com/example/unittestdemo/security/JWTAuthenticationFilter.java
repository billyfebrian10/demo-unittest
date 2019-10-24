package com.example.unittestdemo.security;

import com.example.unittestdemo.constant.CommonConstant;
import com.example.unittestdemo.request.UserLoginRequest;
import com.example.unittestdemo.response.UserLoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(String url, AuthenticationManager authenticationManager){
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserLoginRequest credential = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credential.getUsername(), credential.getPassword(), Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, CommonConstant.JWT_KEY).setSubject(user.getUsername()).compact();

        UserLoginResponse body = new UserLoginResponse(CommonConstant.BEARER + jwt);
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));

    }
}
