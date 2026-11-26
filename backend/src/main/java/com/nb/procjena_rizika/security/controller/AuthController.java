package com.nb.procjena_rizika.security.controller;

import com.nb.procjena_rizika.dto.request.ProcjenaRizikaRequestDto;
import com.nb.procjena_rizika.dto.request.RegistracijaRequestDto;
import com.nb.procjena_rizika.dto.response.ProcjenaRizikaResponseDto;
import com.nb.procjena_rizika.security.dto.JwtDto;
import com.nb.procjena_rizika.security.dto.LoginDto;
import com.nb.procjena_rizika.security.service.AuthService;
import com.nb.procjena_rizika.security.service.UserDetailsServiceImpl;
import com.nb.procjena_rizika.service.logika.RegistracijaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;
    private final RegistracijaService registracijaService;

    @PostMapping("login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        authService.login(loginDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("login-jwt")
    public ResponseEntity<JwtDto> loginJwt(@RequestBody LoginDto loginDto, @RequestParam("rememberMe")Boolean rememberMe ) {
        JwtDto response = authService.loginJwt(loginDto, rememberMe);
        return ResponseEntity.ok(response);


    }

    @PostMapping("register")
    public ResponseEntity <JwtDto> registerJwt(@Valid @RequestBody RegistracijaRequestDto registracijaRequestDto) {
        registracijaService.registracija(registracijaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
