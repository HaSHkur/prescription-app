package com.cmed.prescriptionapp.controller;

import com.cmed.prescriptionapp.domain.UserDomain;
import com.cmed.prescriptionapp.entity.UserEntity;
import com.cmed.prescriptionapp.model.Role;
import com.cmed.prescriptionapp.security.JwtUtil;
import com.cmed.prescriptionapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register/{username}/{password}/{role}")
    public ResponseEntity<UserDomain> register(
            @PathVariable String username,
            @PathVariable String password,
            @PathVariable Role role) {

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role.toString());

        return ResponseEntity.ok(userService.register(user));
    }


    @PostMapping("/login/{username}/{password}")
    public Map<String, String> login(
            @PathVariable String username,
            @PathVariable String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = jwtUtil.generateToken(username);
            return Map.of("token", token);
        } catch (AuthenticationException e) {
            return Map.of("error", "Invalid username or password");
        }
    }

}
