package com.medic.authservice.controller;

import com.medic.authservice.dto.LoginRequestDTO;
import com.medic.authservice.dto.LoginResponseDTO;
import com.medic.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Authenticate user and return JWT token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO) {

        Optional<String> tokenOptional = authService.authenticate(loginRequestDTO);
        return tokenOptional
                .map(token -> ResponseEntity.ok(new LoginResponseDTO(token)))
                .orElseGet(() -> ResponseEntity.status(401).build());


//        if(tokenOptional.isEmpty()){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//        String token = tokenOptional.get();
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//
      }
      @Operation(summary = "Validate token")
      @GetMapping("/validate")
      public ResponseEntity<Void> validateToken(
              @RequestHeader("Authorization") String authHeader){
        //Authorization: Bearer <token>

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(401).build();
        }

        return authService.validateToken(authHeader.substring(7))
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(401).build();
      }

}
