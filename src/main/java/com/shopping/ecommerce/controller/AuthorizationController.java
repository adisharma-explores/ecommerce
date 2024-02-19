package com.shopping.ecommerce.controller;

import com.shopping.ecommerce.config.JwtUtil;
import com.shopping.ecommerce.dao.UserDAO;
import com.shopping.ecommerce.dto.AuthenticationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthorizationController {
    private final AuthenticationManager authenticationManager;
    private final UserDAO userDAO;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails userDetails = userDAO.findUserByEmail(request.getEmail());
        if (userDetails != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", userDetails.getAuthorities());
            return ResponseEntity.ok(jwtUtil.generateToken(userDetails,claims));
        }
        return ResponseEntity.status(400).body("error");
    }

}
