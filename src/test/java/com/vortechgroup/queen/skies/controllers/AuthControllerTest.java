package com.vortechgroup.queen.skies.controllers;

import com.vortechgroup.queen.skies.dto.request.CreateAuthDto;
import com.vortechgroup.queen.skies.dto.response.JwtResponse;
import com.vortechgroup.queen.skies.services.JwtUserDetailsService;
import com.vortechgroup.queen.skies.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUserDetailsService jwtUserDetailsService;
    @Mock
    private JwtUtil jwtUtil;
    private AuthController authController;

    @BeforeEach
    void setUp() {
        authController = new AuthController(authenticationManager, jwtUserDetailsService, jwtUtil);
    }

    @Test
    void testCreateAuthenticationToken_Success() {
        CreateAuthDto createAuthDto = new CreateAuthDto("testuser", "securePass123");
        UserDetails mockUserDetails = mock(UserDetails.class);
        String mockToken = "mock-jwt-token";

        when(jwtUserDetailsService.loadUserByUsername(createAuthDto.getUsername())).thenReturn(mockUserDetails);
        when(jwtUtil.generateToken(mockUserDetails)).thenReturn(mockToken);

        ResponseEntity<JwtResponse> response = authController.createAuthenticationToken(createAuthDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(mockToken, response.getBody().getToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUserDetailsService, times(1)).loadUserByUsername(createAuthDto.getUsername());
        verify(jwtUtil, times(1)).generateToken(mockUserDetails);
    }

    @Test
    void testCreateAuthenticationToken_InvalidCredentials() {
        CreateAuthDto createAuthDto = new CreateAuthDto("testuser", "wrongPass");
        doThrow(new RuntimeException("Invalid credentials"))
                .when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        Exception exception = assertThrows(RuntimeException.class, () ->
                authController.createAuthenticationToken(createAuthDto));

        assertEquals("Invalid credentials", exception.getMessage());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUserDetailsService, never()).loadUserByUsername(anyString());
        verify(jwtUtil, never()).generateToken(any(UserDetails.class));
    }
}
