package org.causwengteam13.issuetrackerserver.service.implement;

import org.causwengteam13.issuetrackerserver.provider.JwtProvider;
import org.causwengteam13.issuetrackerserver.repository.UserRespository;
import org.causwengteam13.issuetrackerserver.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.causwengteam13.issuetrackerserver.domain.user.entity.User;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.request.auth.SignInRequestDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.request.auth.SignUpRequestDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.auth.SignUpResponseDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.ResponseDto;
import org.causwengteam13.issuetrackerserver.infrastructure.querydsl.project.dto.response.auth.SignInResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRespository userRepository;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {

            Long id = dto.getId();
            boolean existedEmail = userRepository.existsById(id);
            if(existedEmail) return SignUpResponseDto.duplicateId();

            String name = dto.getName();
            boolean existedName = userRepository.existsByName(name);
            if(existedName) return SignUpResponseDto.duplicateName();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            User UserEntity = new User(dto);
            userRepository.save(UserEntity);
            

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;

        try {
            Long id = dto.getId();
            User userEntity = userRepository.findByid(id);
            if(userEntity==null) return SignInResponseDto.signInFail();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMathced = passwordEncoder.matches(password, encodedPassword);
            if(!isMathced) return SignInResponseDto.signInFail();

            token = jwtProvider.create(id);
             
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
    
}
