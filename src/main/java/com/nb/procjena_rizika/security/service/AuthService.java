package com.nb.procjena_rizika.security.service;

import com.nb.procjena_rizika.security.dto.JwtDto;
import com.nb.procjena_rizika.security.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;
    public void login(LoginDto loginDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        authenticationProvider.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public JwtDto loginJwt(LoginDto loginDto, Boolean rememberMe) {
        String principal = loginDto.getUsername();
        String credentials = loginDto.getPassword();
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(principal, credentials));

        return jwtService.generate(authentication,rememberMe);

    }

}
