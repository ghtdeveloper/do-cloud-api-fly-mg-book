package com.vortechgroup.queen.skies.controllers;


import com.vortechgroup.queen.skies.dto.request.CreateAuthDto;
import com.vortechgroup.queen.skies.dto.response.JwtResponse;
import com.vortechgroup.queen.skies.services.JwtUserDetailsService;
import com.vortechgroup.queen.skies.utils.JwtUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1.0/fly/auth/login")
public record AuthController(AuthenticationManager authenticationManager,JwtUserDetailsService jwtUserDetailsService,JwtUtil jwtUtil) {
    @PostMapping("/sign")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@Valid @RequestBody CreateAuthDto createAuthDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(createAuthDto.getUsername(), createAuthDto.getPassword()));
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(createAuthDto.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwtUtil.generateToken(userDetails)));
    }
}
