package com.nb.procjena_rizika.security.controller;

import com.nb.procjena_rizika.security.dto.LoginDto;
import com.nb.procjena_rizika.security.service.AuthService;
import com.nb.procjena_rizika.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        authService.login(loginDto);
        return ResponseEntity.ok().build();
    }
}
