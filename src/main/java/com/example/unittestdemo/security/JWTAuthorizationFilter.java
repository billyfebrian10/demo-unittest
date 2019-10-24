package com.example.unittestdemo.security;

import com.example.unittestdemo.constant.CommonConstant;
import com.example.unittestdemo.domain.User;
import com.example.unittestdemo.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if(header == null){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
        if(authentication == null){
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        token = token.replace("Bearer ","");
        String username = null;
        try {
            username = Jwts.parser().setSigningKey(CommonConstant.JWT_KEY).parseClaimsJws(token).getBody().getSubject();
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
