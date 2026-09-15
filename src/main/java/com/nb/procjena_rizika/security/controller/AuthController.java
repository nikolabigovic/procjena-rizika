package com.nb.procjena_rizika.security.controller;

import com.nb.procjena_rizika.security.dto.JwtDto;
import com.nb.procjena_rizika.security.dto.LoginDto;
import com.nb.procjena_rizika.security.service.AuthService;
import com.nb.procjena_rizika.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        authService.login(loginDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("login-jtw")
    public ResponseEntity<JwtDto> loginJwt(@RequestBody LoginDto loginDto, @RequestParam("rememberMe")Boolean rememberMe ) {
        JwtDto response = authService.loginJwt(loginDto, rememberMe);
        return ResponseEntity.ok(response);
    }
}
