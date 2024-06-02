package org.causwengteam13.issuetrackerserver.service;

import org.springframework.http.ResponseEntity;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.request.auth.SignInRequestDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.request.auth.SignUpRequestDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.auth.SignUpResponseDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.auth.SignInResponseDto;




public interface AuthService {

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
