package org.causwengteam13.issuetrackerserver.controller;

import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.causwengteam13.issuetrackerserver.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.request.auth.SignInRequestDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.request.auth.SignUpRequestDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.auth.SignInResponseDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.auth.SignUpResponseDto;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
    
    

}