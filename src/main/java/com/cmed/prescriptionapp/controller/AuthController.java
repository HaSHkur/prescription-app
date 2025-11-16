package com.cmed.prescriptionapp.controller;

import com.cmed.prescriptionapp.domain.UserDomain;
import com.cmed.prescriptionapp.entity.UserEntity;
import com.cmed.prescriptionapp.domain.LoginRequest;
import com.cmed.prescriptionapp.domain.RegistrationRequest;
import com.cmed.prescriptionapp.security.JwtUtil;
import com.cmed.prescriptionapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserDomain> register(@RequestBody RegistrationRequest request) {

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        return ResponseEntity.ok(userService.register(user));
    }


    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            String token = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
            return Map.of("token", token);
        } catch (AuthenticationException e) {
            return Map.of("error", "Invalid username or password");
        }
    }

}
